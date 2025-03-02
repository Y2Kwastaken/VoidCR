package sh.miles.voidcr.world.position;

import sh.miles.voidcr.world.Chunk;
import sh.miles.voidcr.world.block.BlockState;

/**
 * Represents a BlockPos bounded to a chunk
 * <p>
 * Any operation in a LocalBlockPos fails if its result exceeds 15
 * <p>
 * LocalBlockPos should never be stored long term. It is recommended they be stored as {@link BlockPos} see
 * {@link #unbind()} as an alternative
 *
 * @since 0.3.26
 */
public interface LocalBlockPos extends IntPosition<LocalBlockPos> {

    /**
     * Sets the block state of this block position
     *
     * @param state the state to set
     * @return this position
     * @since 0.3.27
     */
    LocalBlockPos setBlockState(BlockState state);

    /**
     * Gets the block state of this position
     *
     * @return the state
     * @since 0.3.27
     */
    BlockState getBlockState();

    /**
     * Gets the chunk that this LocalBlocKPos is bound too
     *
     * @return the chunk associated with this position
     * @since 0.3.26
     */
    Chunk chunk();

    /**
     * Returns a copy of this LocalBlockPos not bound to any Chunk
     *
     * @param expand true to expand the relative chunk coordinates into their global world coordinates
     * @return a new BlockPos
     * @since 0.3.26
     */
    BlockPos unbind(boolean expand);

    /**
     * Returns a copy of this LocalBlockPos not bound to any Chunk
     * <p>
     * Note if this BlockPos was originally truncated per explanation in {@link BlockPos#bindTo(Chunk, boolean)} see
     * {@link #unbind(boolean)}
     *
     * @return a new BlockPos
     * @since 0.3.26
     */
    BlockPos unbind();
}
