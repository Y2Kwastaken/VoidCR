package sh.miles.voidcr.plugin.lifecycle.event.entity;

import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.world.World;

/**
 * Represents an event like structure of data that is shared between all EntityEvents
 * <p>
 * Note this class is not an actual event
 *
 * @since 0.3.22
 */
public interface EntityEvent {

    /**
     * Gets the entity this event involves
     *
     * @return the entity
     * @since 0.3.22
     */
    Entity getEntity();

    /**
     * Gets the world this event involves
     *
     * @return the world
     * @since 0.3.22
     */
    World getWorld();
}
