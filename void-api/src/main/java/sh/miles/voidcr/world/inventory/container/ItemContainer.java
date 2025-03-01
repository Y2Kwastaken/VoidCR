package sh.miles.voidcr.world.inventory.container;

import sh.miles.voidcr.world.inventory.item.ItemStack;

/**
 * Represents a container, which stores item
 *
 * @since 0.3.24
 */
public interface ItemContainer {

    /**
     * Sets a copy of the given item in the container, removing the old item at that location
     *
     * @param slot the slot to set the item at
     * @param item the item to set
     * @since 0.3.24
     */
    void setItem(int slot, ItemStack item);

    /**
     * Adds a copy item to the container
     *
     * @param item the item to add
     * @return true if the add was successful, otherwise false
     * @since 0.3.24
     */
    boolean addItem(ItemStack item);

    /**
     * Checks if the given slot has an item in it
     *
     * @param slot the slot to check at
     * @return true if the slot has an item, otherwise false
     * @since 0.3.24
     */
    boolean hasItem(int slot);

    /**
     * Gets the index of the first empty slot
     *
     * @return the index of the first empty slot
     * @since 0.3.24
     */
    int getFirstEmpty();

    /**
     * Checks whether or not this item container is empty
     *
     * @return true if empty, otherwise false
     * @since 0.3.24
     */
    boolean isEmpty();

    /**
     * Gets the size of the item container
     *
     * @return the size
     * @since 0.3.24
     */
    int getSize();

    /**
     * Clears the entire item container
     *
     * @since 0.3.27
     */
    void clear();

    /**
     * Updates all remotes viewing this container
     *
     * @since 0.3.24
     */
    void updateRemote();
}
