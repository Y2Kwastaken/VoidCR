package sh.miles.voidcr.impl.plugin.lifecycle;

import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.BiConsumer;

public class VoidObserverContainer<C> {

    private final SortedSet<VoidObserverHolder<C>> events;

    public VoidObserverContainer() {
        this.events = new TreeSet<>();
    }

    public int observe(final LifecycleAware<C> owner, final BiConsumer<LifecycleEvent<C>, Integer> event, final int priority) {
        final VoidObserverHolder<C> holder = new VoidObserverHolder<>(priority, owner, event);
        events.add(holder);
        return holder.getId();
    }

    public boolean dismiss(final LifecycleAware<C> owner, final int observerId) {
        return events.removeIf((holder) -> holder.isOwner(owner) && holder.getId() == observerId);
    }

    public boolean dismissAll(final LifecycleAware<C> owner) {
        return events.removeIf((holder) -> holder.isOwner(owner));
    }

    public void call(LifecycleEvent<C> event) {
        for (final VoidObserverHolder<C> holder : events) {
            holder.observe(event);
        }
    }
}
