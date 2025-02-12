package sh.miles.voidcr.impl.plugin.lifecycle;

import com.google.common.base.Preconditions;
import sh.miles.voidcr.impl.server.VoidServer;
import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.plugin.lifecycle.LifecycleManager;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.server.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class VoidLifecycleManager<C> implements LifecycleManager<C> {

    private final C context;
    private final Map<Class<? extends LifecycleEvent<C>>, VoidObserverContainer<? extends LifecycleEvent<C>, C>> observers;

    public VoidLifecycleManager(C context) {
        this.context = context;
        this.observers = new HashMap<>();
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
            System.out.println("No containers");
            return null;
        }

        container.call(realized);
        return realized;
    }

    public static <T extends LifecycleEvent<Server>> T callServerEvent(final Function<Server, T> event) {
        return VoidServer.SERVER.getLifecycle().call(event);
    }

}
