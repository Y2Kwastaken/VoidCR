package sh.miles.voidcr.impl.plugin.lifecycle.command;

import com.google.common.base.Preconditions;
import sh.miles.voidcr.impl.plugin.lifecycle.VoidLifecycleManager;
import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.plugin.lifecycle.command.CommandContextBuilder;
import sh.miles.voidcr.plugin.lifecycle.command.CommandExecutor;

import java.util.HashMap;
import java.util.Map;

public class VoidCommandContextHolder<C> implements CommandContextBuilder<C> {

    private String name;
    private String description = "No description provided for this command";
    private LifecycleAware<C> preferredSource;
    private LifecycleAware<C> owner;
    private Map<Class<?>, LifecycleAware<C>> preferredSpecifics = new HashMap<>();
    private CommandExecutor executor;
    private boolean yieldOnConflict = true;
    private VoidLifecycleManager<C> lifecycle;


    @Override
    public CommandContextBuilder<C> name(final String name) {
        Preconditions.checkArgument(name != null, "The provided name must not be null");
        this.name = name;
        return this;
    }

    @Override
    public CommandContextBuilder<C> description(final String description) {
        Preconditions.checkArgument(description != null, "The provided description must not be null");
        this.description = description;
        return this;
    }

    @Override
    public CommandContextBuilder<C> executor(final CommandExecutor executor) {
        Preconditions.checkArgument(executor != null, "The provided executor must not be null");
        this.executor = executor;
        return this;
    }

    @Override
    public CommandContextBuilder<C> yieldOnConflict(final boolean doYield) {
        this.yieldOnConflict = doYield;
        return this;
    }

    @Override
    public CommandContextBuilder<C> preferSourceForTypes(final LifecycleAware<C> owner, final Class<?>... types) {
        Preconditions.checkArgument(owner != null, "The provided owner of these types must not be null");
        Preconditions.checkArgument(types != null, "The types provided must not be null");
        for (final Class<?> clazz : types) {
            this.preferredSpecifics.put(clazz, owner);
        }
        return this;
    }

    @Override
    public CommandContextBuilder<C> preferArgumentSource(final LifecycleAware<C> owner) {
        Preconditions.checkArgument(owner != null, "The given preferred argument source must not be null");
        this.preferredSource = owner;
        return this;
    }

    public void setLifecycle(final VoidLifecycleManager<C> lifecycle) {
        this.lifecycle = lifecycle;
    }

    public void setOwner(final LifecycleAware<C> owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public LifecycleAware<C> getOwner() {
        return owner;
    }

    VoidLifecycleManager<C> getLifecycle() {
        return lifecycle;
    }

    String getDescription() {
        return description;
    }

    LifecycleAware<C> getPreferredSource(Class<?> type) {
        final var preferred = preferredSpecifics.get(type);
        if (preferred == null && preferredSource != null) {
            return this.preferredSource;
        }
        return this.owner;
    }

    CommandExecutor getExecutor() {
        return executor;
    }

    public boolean isYieldOnConflict() {
        return yieldOnConflict;
    }
}
