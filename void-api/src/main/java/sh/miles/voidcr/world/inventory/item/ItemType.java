package sh.miles.voidcr.world.inventory.item;

import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.block.BlockState;
import sh.miles.voidcr.world.block.BlockType;

/**
 * Represents a type Item that can be in a container
 * <p>
 * This class only contains static entries of only pure items. BlockType's and their equivalent items can be found at
 * {@link BlockType} by using {@link BlockType#getAllBlockStates()} and fetching items via
 * {@link BlockState#getItemType()}. Because this is such an odd structure this is likely subject to change in the
 * future, with a large prior notice.
 *
 * @since 0.3.22
 */
public interface ItemType extends Keyed {

    ItemType AXE_ALUMINIUM = item("axe_aluminium");
    ItemType AXE_IRON = item("axe_iron");
    ItemType AXE_STONE = item("axe_stone");
    ItemType FLUID_VACUUM = item("fluid_vacuum");
    ItemType INGOT_ALUMINIUM = item("ingot_aluminium");
    ItemType INGOT_IRON = item("ingot_iron");
    ItemType LASER_GUN = item("laser_gun");
    ItemType LATEX = item("latex");
    ItemType MEDKIT_GOLD = item("medkit_gold");
    ItemType MEDKIT = item("medkit");
    ItemType PICKAXE_ALUMINIUM = item("pickaxe_aluminium");
    ItemType PICKAXE_IRON = item("pickaxe_iron");
    ItemType PICKAXE_STONE = item("pickaxe_stone");
    ItemType RUBBER_BALL = item("rubber_ball");
    ItemType SHOVEL_ALUMINIUM = item("shovel_aluminium");
    ItemType SHOVEL_IRON = item("shovel_iron");
    ItemType SHOVEL_STONE = item("shovel_stone");
    ItemType STICK = item("stick");

    /**
     * Gets the maximum stack size of this item type
     *
     * @return the maximum stack size
     * @since 0.3.22
     */
    int getMaxStackSize();

    /**
     * Gets whether or not the given item type is within the creative catalog
     *
     * @return true if in the catalog, otherwise false
     * @since 0.3.22
     */
    boolean isInCatalog();

    /**
     * Utility helper for a cast to {@link #key()}
     * <p>
     * ItemKeys are generally more permissible than {@link NamedKey}
     *
     * @return the item key
     * @since 0.3.23
     */
    ItemKey getItemKey();

    private static ItemType item(String key) {
        return Registries.ITEM.get(ItemKey.cosmicReach(key));
    }
}
