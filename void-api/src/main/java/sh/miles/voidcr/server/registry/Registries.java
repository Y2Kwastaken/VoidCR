package sh.miles.voidcr.server.registry;

import sh.miles.voidcr.server.VoidCR;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.world.block.BlockType;

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

    private static <E extends Keyed> Registry<E> registry(Class<E> clazz) {
        return VoidCR.getMagic().getRegistry(clazz);
    }

}
