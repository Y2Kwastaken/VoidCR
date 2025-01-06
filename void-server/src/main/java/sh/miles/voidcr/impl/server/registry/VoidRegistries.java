package sh.miles.voidcr.impl.server.registry;

import finalforeach.cosmicreach.blocks.Block;
import sh.miles.voidcr.impl.world.block.VoidBlockType;
import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.server.registry.Registry;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.world.block.BlockType;

public final class VoidRegistries implements Registries {

    public static <V extends Keyed> Registry<?> createRegistry(Class<V> apiClass) {
        if (apiClass == BlockType.class) {
            return VoidRegistry.fromNaiveInternalSource(Block.blocksByStringId.values(), VoidBlockType::new);
        }

        throw new IllegalArgumentException("No known registry for type " + apiClass.getName());
    }
}
