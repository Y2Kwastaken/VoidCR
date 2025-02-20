package sh.miles.voidcr.world.block;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.NamedKey;

import java.util.Collection;

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
     * Gets the block state of the given key
     *
     * @param key the key of the block state to fetch
     * @return the block state fetched
     * @since 0.3.14
     */
    @Nullable
    BlockState getBlockState(NamedKey key);

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
