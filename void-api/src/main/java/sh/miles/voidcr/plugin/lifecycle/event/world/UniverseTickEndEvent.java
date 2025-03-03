package sh.miles.voidcr.plugin.lifecycle.event.world;

import sh.miles.voidcr.world.Universe;

/**
 * An event triggered when a tick ends for a given Universe
 *
 * @since 0.3.27
 */
public interface UniverseTickEndEvent {
    /**
     * Gets the current time
     * <p>
     * Time can be modified on the universe object {@link Universe#setTime(long)}, this value is not an accurate count
     * of ticks elapsed since the server has started
     *
     * @return the current time
     * @since 0.3.27
     */
    long getCurrentTime();

    /**
     * Gets the universe the tick ended in
     *
     * @return the universe
     * @since 0.3.27
     */
    Universe getUniverse();
}
