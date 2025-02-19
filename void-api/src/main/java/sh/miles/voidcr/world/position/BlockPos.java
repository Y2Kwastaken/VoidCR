package sh.miles.voidcr.world.position;

import sh.miles.voidcr.server.VoidCR;
import sh.miles.voidcr.util.serialize.ByteSerializable;
import sh.miles.voidcr.world.Chunk;

/**
 * Represents a Block position
 *
 * @since 0.3.26
 */
public interface BlockPos extends IntPosition<BlockPos>, ByteSerializable {

    /**
     * Binds the current BlockPos to the provided chunk
     *
     * @param chunk    the chunk to bind this BlockPos too
     * @param truncate true to truncate this BlockPos into relative chunk  coordinates
     * @return a new LocalBlockPos
     */
    LocalBlockPos bindTo(Chunk chunk, boolean truncate) throws IllegalStateException;

    /**
     * Binds the current BlockPos to the provided chunk
     *
     * @param chunk the chunk to bind this BlockPos too
     * @return a new LocalBlockPos
     * @throws IllegalStateException thrown if this location exceeds 15 in any value x, y, or z
     * @since 0.3.26
     */
    LocalBlockPos bindTo(Chunk chunk) throws IllegalStateException;

    /**
     * Creates a BlockPos from the provided information
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     * @return the newly created BlockPos
     * @since 0.3.26
     */
    static BlockPos create(final int x, final int y, final int z) {
        return VoidCR.getMagic().createBlockPos(x, y, z);
    }

    static BlockPos fromBytes(byte[] input) {
        return VoidCR.getMagic().deserialize(BlockPos.class, input);
    }
}
