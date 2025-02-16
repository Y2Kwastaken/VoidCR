package sh.miles.voidcr.server.registry;

import sh.miles.voidcr.server.VoidCR;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.block.BlockType;
import sh.miles.voidcr.world.inventory.item.ItemProperty;
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

    /**
     * The Item Property registry
     * <p>
     * Item Properties themselves are not a concept internally, thus this registry is the invention of VoidCR itself,
     * the long term stability is based off of a likely stable and somewhat flexible API design, however it is possible
     * this Registry might be deprecated in the future. All keys for this registry are under the
     * {@link NamedKey#VOID_CR} namespace.
     *
     * @since 0.3.23
     */
    Registry<ItemProperty> ITEM_PROPERTY = registry(ItemProperty.class);

    private static <E extends Keyed> Registry<E> registry(Class<E> clazz) {
        return VoidCR.getMagic().getRegistry(clazz);
    }

}
