package sh.miles.voidcr.plugin.lifecycle;

import org.jspecify.annotations.NullMarked;

/**
 * Some object, which is aware of a lifecycle
 *
 * @param <C> some initialization context
 * @since 0.3.22
 */
@NullMarked
public interface LifecycleAware<C> {
    /**
     * Initializes this lifecycle aware object
     * <p>
     * Storing a context object outside of this method is potentially dangerous as it could disrupt the delicate balance
     * that lifecycles have. It is recommended that all contexts be used exclusively inside of the initialize and
     * disable method.
     *
     * @param context the context for this lifecycle initialization
     * @since 0.3.22
     */
    void initialize(C context);

    /**
     * Disables/Unloads this lifecycle aware object
     * <p>
     * Storing a context object outside of this method is potentially dangerous as it could disrupt the delicate balance
     * that lifecycles have. It is recommended that all contexts be used exclusively inside of the initialize and
     * disable method.
     *
     * @param context the context for this lifecycle disabling
     * @since 0.3.22
     */
    void disable(C context);
}
