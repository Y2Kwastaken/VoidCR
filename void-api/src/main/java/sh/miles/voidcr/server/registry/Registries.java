package sh.miles.voidcr.server.registry;

import sh.miles.voidcr.server.VoidCR;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.world.block.BlockType;
import sh.miles.voidcr.world.inventory.item.ItemType;

/**
 * Access for registries
 *
 * @since 0.3.14
 */
public interface Registries {

    /**
     * The Block Type registry
     *
     * @since 0.3.14
     */
    Registry<BlockType> BLOCK = registry(BlockType.class);

    /**
     * The Item Type registry
     *
     * @since 0.3.22
     */
    Registry<ItemType> ITEM = registry(ItemType.class);

    private void init() { // hack to init all registries
    }

    private static <E extends Keyed> Registry<E> registry(Class<E> clazz) {
        return VoidCR.getMagic().getRegistry(clazz);
    }

}
