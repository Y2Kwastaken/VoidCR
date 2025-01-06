package sh.miles.voidcr.world.block;

import org.jspecify.annotations.Nullable;

/**
 * Represents block data stored within a chunk. BlockData is always mutable.
 *
 * @param <V> the type of state stored on this block data. Almost always occupied by {@link BlockState}
 * @since 0.3.14
 */
public interface BlockData<V> {

    /**
     * Gets the state off of this BlockData
     *
     * @return the state of the data
     * @since 0.3.14
     */
    @Nullable
    V getState();

    /**
     * Sets the state of this block data
     *
     * @param state the state of this block data
     * @since 0.3.14
     */
    void setState(V state);
}
