package sh.miles.voidcr.plugin.lifecycle.event.entity;

import sh.miles.voidcr.entity.Entity;

/**
 * An interface modeling base data required for an entity damage event
 *
 * @since 0.3.23
 */
public interface EntityDamageEvent extends EntityEvent {

    /**
     * Gets the amount of invulnerability frames to be applied after the event
     *
     * @return the amount of invulnerability frames applied
     * @since 0.3.22
     */
    int getInvulnerabilityFrames();

    /**
     * Gets the amount of damage done during this event
     *
     * @return the damage amount
     * @since 0.3.22
     */
    float getDamage();

    /**
     * Gets the targeted entity
     *
     * @return the targeted entity
     * @since 0.3.22
     */
    Entity getDamager();

}
