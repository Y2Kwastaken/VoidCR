package sh.miles.voidcr.world.block.entity;

import sh.miles.voidcr.world.inventory.container.ItemContainerOwner;

/**
 * Represents a furnace BlockEntity
 *
 * @since 0.3.26
 */
public interface BlockEntityFurnace extends BlockEntity, ItemContainerOwner {

    /**
     * Gets how many fuel ticks the furnace "started" with when it began smelting a recipe. If this method returns 0 it
     * can be safely assumed the furnace is not currently smelting
     *
     * @return the amount of starting fuel ticks, or 0 if there is no recipe ongoing
     * @since 0.3.26
     */
    int getStartingFuelTicks();

    /**
     * Gets the current amount of fuel ticks remaining in the current smelting recipe. If
     * {@link #getStartingFuelTicks()} returns 0 it can be assumed this method will as well
     *
     * @return the amount of fuel ticks left, or 0 if there is no recipe ongoing
     * @since 0.3.26
     */
    int getFuelTicks();

    /**
     * Gets the amount of ticks into the recipe the furnace currently is, this method will return 0 if no recipe is
     * active
     *
     * @return the amount of ticks in progress, or 0 if no recipe is started
     * @since 0.3.26
     */
    int getProgressTicks();

    /**
     * Gets the "destination" progress ticks as the recipe completes, once {@link #getProgressTicks()} reaches the
     * return of this value, the furnace will complete the recipe.
     * <p>
     * As of 0.3.26 all recipes return "64", for their value, while this is a constant it is recommended to use this
     * method because this could change in the future.
     *
     * @return the maximum progress ticks, or 0 if no recipe is started
     * @since 0.3.26
     */
    int getMaxProgressTicks();
}
