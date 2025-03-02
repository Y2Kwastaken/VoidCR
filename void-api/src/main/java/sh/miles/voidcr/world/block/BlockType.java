package sh.miles.voidcr.world.block;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.NamedKey;

import java.util.Collection;
import java.util.Map;

public interface BlockType extends Keyed {

    BlockType AIR = block("air");
    BlockType ALUMINIUM_PANEL = block("aluminium_panel");
    BlockType ASPHALT = block("asphalt");
    BlockType BOOM_BOX = block("boombox");
    BlockType BRICKS = block("bricks");
    BlockType C4 = block("c4");
    BlockType CHEESE = block("cheese");
    BlockType COCONUT = block("coconut");
    BlockType CRATE_WOODEN = block("crate_wooden");
    BlockType DEBUG = block("debug");
    BlockType DIRT = block("dirt");
    BlockType FURNACE = block("furnace");
    BlockType GLASS = block("glass");
    BlockType GOLD_BLOCK = block("gold_block");
    BlockType GRASS = block("grass");
    BlockType HAZARD = block("hazard");
    BlockType ICE = block("ice");
    BlockType LEAVES_POPLAR = block("leaves_poplar");
    BlockType LIGHT = block("light");
    BlockType LUNAR_SOIL_PACKED = block("lunar_soil_packed");
    BlockType LUNAR_SOIL = block("lunar_soil");
    BlockType MAGMA = block("magma");
    BlockType METAL_PANEL = block("metal_panel");
    BlockType ORE_BAUXITE = block("ore_bauxite");
    BlockType ORE_GOLD = block("ore_gold");
    BlockType ORE_IRON = block("ore_iron");
    BlockType RUBBER_BLOCK = block("rubber_block");
    BlockType SAND = block("sand");
    BlockType SAPLING_POPLAR = block("sapling_poplar");
    BlockType SNOW = block("snow");
    BlockType STONE_BASALT = block("stone_basalt");
    BlockType STONE_GABBRO = block("stone_gabbro");
    BlockType STONE_GRAVEL = block("stone_gravel");
    BlockType STONE_LIMESTONE = block("stone_limestone");
    BlockType TEXT_DISPLAY = block("text_display");
    BlockType TREE_LOG = block("tree_log");
    BlockType WATER = block("water");
    BlockType WOOD_DOOR = block("wood_door");
    BlockType WOOD_PLANKS = block("wood_planks");

    /**
     * Gets a block state with the given key and value parameters
     *
     * @param parameterKey   the parameter key
     * @param parameterValue the parameter value
     * @return the state, or null if no state with the parameter was found
     * @since 0.3.27
     */
    @Nullable
    BlockState getBlockState(String parameterKey, Object parameterValue);

    /**
     * Gets a block state with at least the given parameters
     *
     * @param parameters the minimum parameters to have
     * @return the state, or null if no state with the parameters was found
     * @since 0.3.27
     */
    @Nullable
    BlockState getBlockState(Map<String, Object> parameters);

    /**
     * Gets a block state of this type with the following exact parameters
     *
     * @param parameters the parameters
     * @return the block state, or null if no block state with those parameters were found
     * @since 0.3.27
     */
    @Nullable
    BlockState getExactBlockState(Map<String, Object> parameters);

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
        return Registries.BLOCK.getOrThrow(NamedKey.cosmicReach(key));
    }
}
