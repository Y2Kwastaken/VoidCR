package sh.miles.voidcr.world.block;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.NamedKey;

import java.util.Collection;

public interface BlockType extends Keyed {

    BlockType AIR = block("air");
    BlockType GRASS = block("grass");
    BlockType STONE_BASALT = block("stone_basalt");
    BlockType DIRT = block("dirt");
    BlockType WOOD_PLANKS = block("wood_planks");
    BlockType CRATE_WOOD = block("crate_wooden");
    BlockType HAZARD = block("hazard");
    BlockType SAND = block("sand");
    BlockType TREE_LOG = block("tree_log");
    BlockType LEAVES = block("leaves");
    BlockType COCONUT = block("coconut");
    BlockType SNOW = block("snow");
    BlockType WATER = block("water");
    BlockType LUNAR_SOIL = block("lunar_soil");

    /**
     * Gets the block state of the given key
     *
     * @param key the key of the block state to fetch
     * @return the block state fetched
     * @since 0.3.14
     */
    @Nullable
    BlockState getBlockState(NamedKey key) throws IllegalArgumentException;

    /**
     * Gets all block states of this block type
     *
     * @return all common block states
     * @since 0.3.14
     */
    Collection<BlockState> getAllBlockStates();

    /**
     * Gets the default block state for this block type
     *
     * @return the default block state
     * @since 0.3.14
     */
    BlockState getDefaultBlockState();

    private static BlockType block(String key) {
        return Registries.BLOCK.get(NamedKey.cosmicReach(key));
    }
}
