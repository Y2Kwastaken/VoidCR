package sh.miles.voidcr.impl.plugin.lifecycle;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import finalforeach.cosmicreach.chat.commands.Command;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.Main;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.impl.plugin.lifecycle.command.VoidBundledCommandSupplier;
import sh.miles.voidcr.impl.plugin.lifecycle.command.VoidCommandContextHolder;
import sh.miles.voidcr.impl.plugin.lifecycle.command.VoidCommandWrapper;
import sh.miles.voidcr.impl.plugin.lifecycle.command.arguments.DoubleArgument;
import sh.miles.voidcr.impl.plugin.lifecycle.command.arguments.FloatArgument;
import sh.miles.voidcr.impl.plugin.lifecycle.command.arguments.IntegerArgument;
import sh.miles.voidcr.impl.plugin.lifecycle.command.arguments.LongArgument;
import sh.miles.voidcr.impl.plugin.lifecycle.command.arguments.PlayerEntityArgument;
import sh.miles.voidcr.impl.plugin.lifecycle.command.arguments.StringArgument;
import sh.miles.voidcr.impl.server.VoidServer;
import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.plugin.lifecycle.LifecycleManager;
import sh.miles.voidcr.plugin.lifecycle.command.CommandArgumentResolver;
import sh.miles.voidcr.plugin.lifecycle.command.CommandContextBuilder;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.server.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public final class VoidLifecycleManager<C> implements LifecycleManager<C> {

    private final C context;
    private final Map<Class<? extends LifecycleEvent<C>>, VoidObserverContainer<? extends LifecycleEvent<C>, C>> observers;
    private final Map<Class<?>, CommandArgumentResolverContainer<C, ?>> arguments;
    private final Map<Class<?>, CommandArgumentResolver<?>> builtInArguments;

    public VoidLifecycleManager(C context) {
        this.context = context;
        this.observers = new HashMap<>();
        this.arguments = new HashMap<>();
        this.builtInArguments = ImmutableMap.<Class<?>, CommandArgumentResolver<?>>builder()
                .put(Double.class, new DoubleArgument())
                .put(double.class, new DoubleArgument())
                .put(Float.class, new FloatArgument())
                .put(float.class, new FloatArgument())
                .put(Integer.class, new IntegerArgument())
                .put(int.class, new IntegerArgument())
                .put(Long.class, new LongArgument())
                .put(long.class, new LongArgument())
                .put(PlayerEntity.class, new PlayerEntityArgument())
                .put(String.class, new StringArgument())
                .build();
    }

    @Override
    public void registerCommand(final LifecycleAware<C> owner, final Consumer<CommandContextBuilder<C>> builder) throws IllegalArgumentException {
        Preconditions.checkArgument(owner != null, "The provided owner must not be null");
        Preconditions.checkArgument(builder != null, "The provided command context builder must not be null");
        final var holder = new VoidCommandContextHolder<C>();
        builder.accept(holder);
        holder.setLifecycle(this);
        holder.setOwner(owner);

        final var registered = Command.REGISTERED_COMMANDS;

        final var current = registered.get(holder.getName());
        if (current == null) {
            // no conflicts so we're good
            Command.registerCommand(new VoidBundledCommandSupplier<>(holder, VoidCommandWrapper::new), holder.getName());
            return;
        }

        final String namespace = VoidServer.SERVER.getPluginLoader().getPluginName(owner);
        if (holder.isYieldOnConflict()) {
            Command.registerCommand(new VoidBundledCommandSupplier<>(holder, VoidCommandWrapper::new), namespace + ":" + holder.getName());
            return;
        }

        // greedy naming strategy :(... please be kind to your fellow developers
        if (current instanceof VoidBundledCommandSupplier<?> bundled) {
            if (bundled.getContext().isYieldOnConflict()) {
                Command.REGISTERED_COMMANDS.remove(bundled.getContext().getName()); // no aliases supported yet so only have to remove name
                Command.registerCommand(new VoidBundledCommandSupplier<>(holder, VoidCommandWrapper::new), holder.getName());
                final String otherNamespace = VoidServer.SERVER.getPluginLoader().getPluginName(bundled.getContext().getOwner());
                Command.registerCommand(bundled, otherNamespace + ":" + bundled.getContext().getName());
            } else {
                Main.LOGGER.warn("Plugin with namespace {} attempted to register command with name {}, but was failed to register because of competing greedy naming strategies, consider setting yieldOnConflict to true", namespace, holder.getName());
            }
        } else { // we will just overwrite vanilla commands no problem, of course this prays no one does anything silly here
            Command.registerCommand(new VoidBundledCommandSupplier<>(holder, VoidCommandWrapper::new), holder.getName());
        }
    }

    @Override
    public <R> void registerArgumentResolver(final LifecycleAware<C> owner, final Class<R> type, final CommandArgumentResolver<R> argument) throws IllegalArgumentException {
        Preconditions.checkArgument(owner != null, "The command argument resolver must have a non null owner");
        Preconditions.checkArgument(type != null, "The provided type must not be null");
        Preconditions.checkArgument(argument != null, "The provided argument must not be null");
        this.arguments.putIfAbsent(type, new CommandArgumentResolverContainer<>());

        final var container = (CommandArgumentResolverContainer<C, R>) this.arguments.get(type);
        Preconditions.checkArgument(container.load(owner, argument), "The provided argument has already been inserted for this owner");
    }

    /**
     * Internal method for resolving a registered argument resolver
     *
     * @param <R> the type of resolver
     * @return the argument resolver
     */
    @Nullable
    public <R> CommandArgumentResolver<R> resolveArgument(final Class<R> type, final LifecycleAware<C> preferred, @Nullable final LifecycleAware<C> backup) {
        Preconditions.checkArgument(preferred != null, "The provided preferred owner must not be null");

        final var container = (CommandArgumentResolverContainer<C, R>) this.arguments.get(type);
        if (container == null) {
            return null;
        }

        var result = container.query(preferred, backup);
        if (result == null) {
            return (CommandArgumentResolver<R>) builtInArguments.get(type);
        } else {
            return result;
        }
    }

    @Override
    public <T extends LifecycleEvent<C>> int observe(final LifecycleAware<C> owner, final Class<T> eventClass, final BiConsumer<T, Integer> event) {
        return observe(owner, eventClass, LifecycleManager.DEFAULT_PRIORITY, event);
    }

    @Override
    public <T extends LifecycleEvent<C>> int observe(final LifecycleAware<C> owner, final Class<T> eventClass, final int priority, final BiConsumer<T, Integer> event) {
        Preconditions.checkArgument(owner != null, "The observer owner must not be null");
        Preconditions.checkArgument(eventClass != null, "The event class must not be null");
        Preconditions.checkArgument(event != null, "The event must not be null");
        observers.putIfAbsent(eventClass, new VoidObserverContainer<>());

        final VoidObserverContainer<T, C> container = (VoidObserverContainer<T, C>) observers.get(eventClass);
        return container.observe(owner, event, priority);
    }

    @Override
    public boolean dismiss(final LifecycleAware<C> owner, final int observerId) {
        Preconditions.checkArgument(owner != null, "The observer owner must not be null");
        for (final VoidObserverContainer<?, C> value : observers.values()) {
            if (value.dismiss(owner, observerId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean dismissAll(final LifecycleAware<C> owner) {
        Preconditions.checkArgument(owner != null, "The observer owner must not be null");
        boolean dismissedAll = false;
        for (final VoidObserverContainer<?, C> value : observers.values()) {
            dismissedAll = dismissedAll || value.dismissAll(owner);
        }

        return dismissedAll;
    }

    @Override
    public <T extends LifecycleEvent<C>> T call(final Function<C, T> event) {
        final T realized = event.apply(this.context);
        final VoidObserverContainer<T, C> container = (VoidObserverContainer<T, C>) observers.get(realized.getEventClass()); // fix this child is not expected super
        if (container == null) {
            return null;
        }

        container.call(realized);
        return realized;
    }

    public static <T extends LifecycleEvent<Server>> T dispatchEvent(final Function<Server, T> event) {
        return VoidServer.SERVER.getLifecycle().call(event);
    }

}
