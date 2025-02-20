package sh.miles.voidcr.world.block.entity;

/**
 * Represents a BlockEntity for the LaserEmitter block
 *
 * @since 0.3.26
 */
public interface BlockEntityLaserEmitter extends BlockEntity {
    /**
     * Shoots the laser projectile, the next tick
     *
     * @since 0.3.26
     */
    void shoot();

    /**
     * Instantly shoots the laser projectile
     *
     * @since 0.3.26
     */
    void shootNow();

    /**
     * Gets whether or not this BlockEntity laser will shoot the next tick
     *
     * @return true if the laser emitter will shoot the next tick, otherwise false
     * @since 0.3.26
     */
    boolean willShootNextTick();
}
