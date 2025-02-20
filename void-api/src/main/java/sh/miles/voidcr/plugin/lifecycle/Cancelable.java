package sh.miles.voidcr.plugin.lifecycle;

import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;

/**
 * Marks a {@link LifecycleEvent} that can be prevented from running
 *
 * @since 0.3.26
 */
public interface Cancelable {
    /**
     * Sets whether or not this event should be canceled
     *
     * @param canceled true if canceled, false to mark as uncanceled
     * @since 0.3.26
     */
    void setCanceled(boolean canceled);

    /**
     * Gets whether or not this event is canceled
     *
     * @return true if canceled otherwise false
     * @since 0.3.26
     */
    boolean isCanceled();
}
