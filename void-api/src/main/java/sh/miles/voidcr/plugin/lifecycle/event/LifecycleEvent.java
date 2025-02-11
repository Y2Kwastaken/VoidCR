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
}
