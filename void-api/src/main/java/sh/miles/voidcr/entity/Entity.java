package sh.miles.voidcr.entity;

import com.badlogic.gdx.math.Vector3;

/**
 * Represents an entity within CosmicReach
 *
 * @since 0.3.14
 */
public interface Entity {

    /**
     * Gets the range of sight for this entity
     *
     * @return the range of sight
     * @since 0.3.14
     */
    float getRangeOfSight();

    /**
     * Gets the current health of this entity
     *
     * @return the current health
     * @since 0.3.14
     */
    float getHealth();

    /**
     * Gets the maximum possible health of this entity
     *
     * @return the maximum possible health of this entity
     * @since 0.3.14
     */
    float getMaxHealth();

    /**
     * Gets the current position of this entity
     *
     * @return this entity's current position
     */
    Vector3 getPosition();

    /**
     * Gets the current velocity of this entity
     *
     * @return the entity's current velocity
     */
    Vector3 getVelocity();

    /**
     * An Entity Identifier is much like a UUID, where it is used to identify a specific entity
     *
     * @return the identifier for this entity
     * @since 0.3.14
     */
    EntityIdentifier getIdentifier();
}
