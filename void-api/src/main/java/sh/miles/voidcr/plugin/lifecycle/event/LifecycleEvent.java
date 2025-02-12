package sh.miles.voidcr.plugin.lifecycle.event;

import org.jspecify.annotations.NullMarked;

/**
 * Represents an event occurring within a given lifecycle
 *
 * @param <C> the lifecycle context
 * @since 0.3.22
 */
@NullMarked
public interface LifecycleEvent<C> {

    /**
     * Gets the context of this lifecycle event
     *
     * @return the lifecycle context
     * @since 0.3.22
     */
    C getContext();

    /**
     * Gets the event class for this event
     *
     * @return the lifecycle event class
     * @since 0.3.22
     */
    Class<? extends LifecycleEvent<C>> getEventClass();
}
