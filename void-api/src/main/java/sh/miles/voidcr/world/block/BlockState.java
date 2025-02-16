package sh.miles.voidcr.world.block;

import sh.miles.voidcr.world.inventory.item.ItemType;

/**
 * Generally represents the overall state of a {@link BlockData}.
 *
 * @since 0.3.14
 */
public interface BlockState {

    /**
     * Determines whether the state is opaque
     *
     * @return true if opaque
     * @since 0.3.14
     */
    boolean isOpaque();

    /**
     * Determines whether or not you can walk through this block state
     *
     * @return true if can walk through
     * @since 0.3.14
     */
    boolean canWalkThrough();

    /**
     * Determines whether this state is a fluid
     *
     * @return true if a fluid
     * @since 0.3.14
     */
    boolean isFluid();

    /**
     * Gets whether or not this state has block state occludes on any size
     *
     * @return true if occludes at all
     * @since 0.3.14
     */
    boolean isOccluding();

    /**
     * Gets whether or not the given block state is within the creative catalog
     *
     * @return true if in the catalog, otherwise false
     * @since 0.3.14
     */
    boolean isInCatalog();

    /**
     * the type of item this state belongs to
     *
     * @return the item type related to this state
     * @since 0.3.22
     */
    ItemType getItemType();

    /**
     * the type of block this state belongs to
     *
     * @return the block type related to this state
     * @since 0.3.14
     */
    BlockType getBlockType();
}
