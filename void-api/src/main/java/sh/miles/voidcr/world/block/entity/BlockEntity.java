package sh.miles.voidcr.world.block.entity;

import sh.miles.voidcr.world.World;
import sh.miles.voidcr.world.position.BlockPos;

/**
 * Represents a "BlockEntity" within a world. A BlockEntity is a more complex block that can store and provides a
 * variety of different mechanics that regular blocks are not capable of
 *
 * @since 0.3.26
 */
public interface BlockEntity {

    /**
     * Gets the block position of this BlockEntity
     *
     * @return the block pos
     * @since 0.3.26
     */
    BlockPos getBlockPos();

    /**
     * Gets the world associated with this BlockEntity
     *
     * @return the world
     * @since 0.3.26
     */
    World getWorld();

    /**
     * Gets if this BlockEntity is loaded or not
     *
     * @return true if loaded, otherwise false
     * @since 0.3.26
     */
    boolean isLoaded();
}
