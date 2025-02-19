package sh.miles.voidcr.world;

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
}
