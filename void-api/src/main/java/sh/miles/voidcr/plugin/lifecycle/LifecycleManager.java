package sh.miles.voidcr.plugin.lifecycle;

import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Manages the lifecycle of {@link LifecycleAware} objects. This provides extra information about these objects as well
 *
 * @param <C> the initialization context for the {@link LifecycleAware} object
 * @since 0.3.22
 */
@NullMarked
public interface LifecycleManager<C> {

    int MAX_PRIORITY = 99;
    int DEFAULT_PRIORITY = 45;
    int MIN_PRIORITY = 1;

    /**
     * Begins observing an event.
     * <p>
     * The event decided to be observed with this method must either be observed until the end of the lifecycle or
     * dismissed with {@link #dismiss(LifecycleAware, int)} if an event is dismissed it must be re-registered or its
     * logic will not run. Only the owner of the event with the given id may dismiss an observer.
     * <p>
     * This method observes at a standard priority level of 45
     *
     * @param owner      the owner of the event
     * @param eventClass the event class to listen to
     * @param event      the event logic function containing the event and observer id
     * @return the observer id
     * @since 0.3.22
     */
    int observe(LifecycleAware<C> owner, Class<? extends LifecycleEvent<C>> eventClass, BiConsumer<LifecycleEvent<C>, Integer> event);

    /**
     * Begins observing an event.
     * <p>
     * The event decided to be observed with this method must either be observed until the end of the lifecycle or
     * dismissed with {@link #dismiss(LifecycleAware, int)} if an event is dismissed it must be re-registered or its
     * logic will not run. Only the owner of the event with the given id may dismiss an observer.
     *
     * @param owner      the owner of the event
     * @param eventClass the event class to listen to
     * @param priority   the priority that the event has, which must be between {@link #MIN_PRIORITY} and
     *                   {@link #MAX_PRIORITY} inclusively
     * @param event      the event
     * @return the observer id
     */
    int observe(LifecycleAware<C> owner, Class<? extends LifecycleEvent<C>> eventClass, int priority, BiConsumer<LifecycleEvent<C>, Integer> event);

    /**
     * Dismisses an observe, stopping it from being run
     * <p>
     * The owner provided must be the same as the owner of the initial observer
     *
     * @param owner      the owner
     * @param observerId the observer id
     * @return true if the dismissal was successful, otherwise false
     * @since 0.3.22
     */
    boolean dismiss(LifecycleAware<C> owner, int observerId);

    /**
     * Dismisses all observers put out by the given owner.
     * <p>
     * the owner does not need to manually dismiss lifecycle events before the end of the lifecycle this is handled by
     * VoidCR automatically
     *
     * @param owner the owner of events to dismiss
     * @return true if all dismissals were successful, otherwise false
     * @since 0.3.22
     */
    boolean dismissAll(LifecycleAware<C> owner);

    /**
     * Calls the provided event for all observers added through {@link #observe(LifecycleAware, Class, BiConsumer)}
     * <p>
     * This method is here for the expressed purpose of calling custom events. It should likely never be used in
     * conjunction with internals to call already implemented events as this may create unexpected behaviour in plugins
     *
     * @param event the event to construct and call when provided the proper context
     * @since 0.3.22
     */
    void call(Function<C, LifecycleEvent<C>> event);
}
