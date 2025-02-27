package sh.miles.voidcr.world.position;

import sh.miles.voidcr.server.VoidCR;
import sh.miles.voidcr.util.serialize.ByteSerializable;

/**
 * Represents some position in the world
 *
 * @since 0.3.27
 */
public interface Position extends DecimalPosition<Position>, ByteSerializable {
    /**
     * Coerces this position into a block position
     *
     * @return coerces this position into a block position
     * @since 0.3.27
     */
    BlockPos coerce();

    /**
     * Creates a Position from the provided information
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     * @return the newly created BlockPos
     * @since 0.3.26
     */
    static Position create(final float x, final float y, final float z) {
        return VoidCR.getMagic().createPosition(x, y, z);
    }

    static Position fromBytes(byte[] input) {
        return VoidCR.getMagic().deserialize(Position.class, input);
    }
}
