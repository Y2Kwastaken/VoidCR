package sh.miles.voidcr.impl.plugin.lifecycle;

import com.google.common.base.Preconditions;
import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.plugin.lifecycle.LifecycleManager;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class VoidLifecycleManager<C> implements LifecycleManager<C> {

    private final C context;
    private final Map<Class<? extends LifecycleEvent<C>>, VoidObserverContainer<C>> observers;

    public VoidLifecycleManager(C context) {
        this.context = context;
        this.observers = new HashMap<>();
    }

    @Override
    public int observe(final LifecycleAware<C> owner, final Class<? extends LifecycleEvent<C>> eventClass, final BiConsumer<LifecycleEvent<C>, Integer> event) {
        return observe(owner, eventClass, LifecycleManager.DEFAULT_PRIORITY, event);
    }

    @Override
    public int observe(final LifecycleAware<C> owner, final Class<? extends LifecycleEvent<C>> eventClass, final int priority, final BiConsumer<LifecycleEvent<C>, Integer> event) {
        Preconditions.checkArgument(owner != null, "The observer owner must not be null");
        Preconditions.checkArgument(eventClass != null, "The event class must not be null");
        Preconditions.checkArgument(event != null, "The event must not be null");
        observers.putIfAbsent(eventClass, new VoidObserverContainer<>());

        final VoidObserverContainer<T, C> container = observers.get(eventClass);
        return container.observe(owner, event, priority);
    }

    @Override
    public boolean dismiss(final LifecycleAware<C> owner, final int observerId) {
        Preconditions.checkArgument(owner != null, "The observer owner must not be null");
        for (final VoidObserverContainer<T, C> value : observers.values()) {
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
        for (final VoidObserverContainer<T, C> value : observers.values()) {
            dismissedAll = dismissedAll || value.dismissAll(owner);
        }

        return dismissedAll;
    }

    @Override
    public void call(final Function<C, LifecycleEvent<C>> event) {
        final LifecycleEvent<C> realized = event.apply(this.context);
        final VoidObserverContainer<T, C> container = observers.get(event.getClass());
        if (container == null) {
            return;
        }

        container.call(realized);
    }

}
