package sh.miles.voidcr.impl.plugin.lifecycle;

import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;

import java.util.function.BiConsumer;

/*
 * holds event data
 */
@NullMarked
public final class VoidObserverHolder<T extends LifecycleEvent<C>, C> implements Comparable<VoidObserverHolder<T, C>> {

    private static int ID = 0;

    private final int id;
    private final int priority;
    private final LifecycleAware<C> owner;
    private final BiConsumer<T, Integer> event;

    public VoidObserverHolder(final int priority, LifecycleAware<C> owner, BiConsumer<T, Integer> event) {
        if (ID + 1 == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("the observer id has exceeded the int limit. This should really never happen. Make an issue on github if you ever see this");
        }

        id = ID++;
        this.priority = priority;
        this.owner = owner;
        this.event = event;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    public void observe(T event) {
        this.event.accept(event, this.id);
    }

    public boolean isOwner(LifecycleAware<C> owner) {
        return this.owner == owner;
    }

    @Override
    public int compareTo(final VoidObserverHolder<T, C> other) {
        return Integer.compare(this.priority, other.priority);
    }
}
