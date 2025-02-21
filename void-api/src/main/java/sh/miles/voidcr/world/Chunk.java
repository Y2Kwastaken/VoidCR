package sh.miles.voidcr.world;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.world.block.entity.BlockEntity;
import sh.miles.voidcr.world.position.LocalBlockPos;

import java.util.Collection;

/**
 * Represents a 16x16x16 "chunk" of area within a {@link World}. These zones store a variety of useful information that
 * can be utilized for world changing operations
 *
 * @since 0.3.26
 */
public interface Chunk {
    /**
     * The chunks x position
     *
     * @return int x position
     * @since 0.3.26
     */
    int chunkX();

    /**
     * The chunks y position
     *
     * @return int y position
     * @since 0.3.26
     */
    int chunkY();

    /**
     * The chunks y position
     *
     * @return int y position
     * @since 0.3.26
     */
    int chunkZ();

    /**
     * Gets the BlockEntity at the provided location
     *
     * @param x the x value which must fit [0, 16)
     * @param y the y value, which must fit [0, 16)
     * @param z the z value, which must fit [0, 16)
     * @return the block entity if found at the position, otherwise null
     * @since 0.3.26
     */
    BlockEntity getBlockEntity(int x, int y, int z);

    /**
     * Attempts to get a BlockEntity at the provided {@link LocalBlockPos}
     *
     * @param pos the position to get the BlockEntity at
     * @return the block entity if found at the position, otherwise null
     * @since 0.3.26
     */
    @Nullable
    BlockEntity getBlockEntity(LocalBlockPos pos);

    /**
     * Gets all BlockEntities in this chunk
     *
     * @return
     */
    Collection<BlockEntity> getAllBlockEntities();

    /**
     * Gets whether or not this chunk has any block entities in it
     *
     * @return true if there are any block entities, otherwise false
     * @since 0.3.26
     */
    boolean hasBlockEntities();
}
