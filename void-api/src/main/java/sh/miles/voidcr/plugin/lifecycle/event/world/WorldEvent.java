package sh.miles.voidcr.plugin.lifecycle.event.world;

import sh.miles.voidcr.world.World;

/**
 * Represents an event like structure shared between all WorldEvents
 * <p>
 * Note this class is not an actual event
 *
 * @since 0.3.26
 */
public interface WorldEvent {

    /**
     * Gets the world associated with the event
     *
     * @return the world
     * @since 0.3.26
     */
    World getWorld();
}
