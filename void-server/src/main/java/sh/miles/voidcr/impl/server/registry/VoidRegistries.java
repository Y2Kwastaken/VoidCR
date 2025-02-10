package sh.miles.voidcr.impl.server.registry;

import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.ItemThing;
import sh.miles.voidcr.impl.world.block.VoidBlockType;
import sh.miles.voidcr.impl.world.inventory.item.VoidItemType;
import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.server.registry.Registry;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.world.block.BlockType;
import sh.miles.voidcr.world.inventory.item.ItemType;

public final class VoidRegistries implements Registries {

    public static <V extends Keyed> Registry<?> createRegistry(Class<V> apiClass) {
        if (apiClass == BlockType.class) {
            return VoidRegistry.fromNaiveInternalSource(Block.blocksByStringId.values(), VoidBlockType::new);
        }

        if (apiClass == ItemType.class) {
            return VoidRegistry.fromGdxNaiveInternalSource(ItemThing.allItems.values().toArray(), VoidItemType::new);
        }

        throw new IllegalArgumentException("No known registry for type " + apiClass.getName());
    }
}
