package sh.miles.voidcr.entity;

/**
 * Represents a drone trap, which can release a hoard of drones
 *
 * @since 0.3.23
 */
public interface DroneTrapEntity extends Entity {

    /**
     * Releases the drone trap
     * <p>
     * Releasing a drone trap spawns all drones that this drone trap holds
     *
     * @since 0.3.23
     */
    void release();
}
