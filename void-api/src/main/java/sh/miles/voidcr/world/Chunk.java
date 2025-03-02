package sh.miles.voidcr.world;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.world.block.BlockState;
import sh.miles.voidcr.world.block.entity.BlockEntity;
import sh.miles.voidcr.world.position.BlockPos;

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
     * Attempts to get a BlockEntity at the provided {@link BlockPos}
     *
     * @param pos the position to get the BlockEntity at, which must be between 0 and 16
     * @return the block entity if found at the position, otherwise null
     * @since 0.3.26
     */
    @Nullable
    BlockEntity getBlockEntity(BlockPos pos);

    /**
     * Attempts to set the block state at the provided x, y, and z value
     *
     * @param x     the x, which must be no greater than 15 and no less than 0
     * @param y     the y, which must be no greater than 15 and no less than 0
     * @param z     the z, which must be no greater than 15 and no less than 0
     * @param state the block state to set at that position
     * @since 0.3.27
     */
    void setBlockState(int x, int y, int z, BlockState state);

    /**
     * Attempts to get the block state at the provided x, y, and z value
     *
     * @param x the x, which must be no greater than 15 and no less than 0
     * @param y the y, which must be no greater than 15 and no less than 0
     * @param z the z, which must be no greater than 15 and no less than 0
     * @return the block state
     * @since 0.3.27
     */
    BlockState getBlockState(int x, int y, int z);

    /**
     * Attempts to set the block state at the provided {@link BlockPos}
     *
     * @param pos   the position to set the BlockState at, which must be between 0 and 16
     * @param state the block state to set at that position
     * @since 0.3.27
     */
    void setBlockState(BlockPos pos, BlockState state);

    /**
     * Attempts to get a block state at the provided {@link BlockPos}
     *
     * @param pos the position to get the BlockState at, which must be between 0 and 16
     * @return the block state
     * @since 0.3.27
     */
    BlockState getBlockState(BlockPos pos);

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
