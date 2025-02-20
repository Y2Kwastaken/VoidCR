package sh.miles.voidcr.world.inventory.container;

/**
 * Represents an object who owns an item container
 *
 * @since 0.3.26
 */
public interface ItemContainerOwner {
    /**
     * Gets the item container associated with this object
     *
     * @return the item container
     * @since 0.3.26
     */
    ItemContainer getItemContainer();
}
