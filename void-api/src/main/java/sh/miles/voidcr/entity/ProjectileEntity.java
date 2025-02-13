package sh.miles.voidcr.entity;

import org.jspecify.annotations.Nullable;

/**
 * Represents any projectile like entity.
 *
 * @since 0.3.23
 */
public interface ProjectileEntity extends Entity {

    /**
     * Sets the strength of the projectile
     *
     * @param strength the projectile strength
     * @since 0.3.23
     */
    void setStrength(float strength);

    /**
     * Gets the strength of the projectile
     *
     * @return the projectile strength
     * @since 0.3.23
     */
    float getStrength();

    /**
     * Gets the projectile radius
     *
     * @return the projectile radius
     * @since 0.3.23
     */
    float getRadius();

    /**
     * Gets the identifier of the entity who launched the projectile
     *
     * @return the identifier of what entity launched the projectile, or null if there is no source
     * @since 0.3.23
     */
    @Nullable
    EntityIdentifier getProjectileLauncher();
}
