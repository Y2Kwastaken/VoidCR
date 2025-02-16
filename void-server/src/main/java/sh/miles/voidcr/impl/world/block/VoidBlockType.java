package sh.miles.voidcr.impl.world.block;

import finalforeach.cosmicreach.blocks.Block;
import sh.miles.voidcr.impl.util.VoidNamedKey;
import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.block.BlockState;
import sh.miles.voidcr.world.block.BlockType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class VoidBlockType implements BlockType, Mirrored<Block> {

    public static Block toCosmicReach(BlockType type) {
        return Block.getById(((VoidNamedKey) type.key()).getCosmicReachId());
    }

    public static VoidBlockType toVoid(Block block) {
        return (VoidBlockType) Registries.BLOCK.get(NamedKey.key(block.getStringId()));
    }

    private final Block mirror;
    private final NamedKey key;

    public VoidBlockType(Block mirror) {
        this.mirror = mirror;
        this.key = NamedKey.key(mirror.getStringId());
    }

    @Override
    public Block getMirror() {
        return this.mirror;
    }

    @Override
    public NamedKey key() {
        return this.key;
    }

    @Override
    public BlockState getBlockState(final NamedKey key) throws IllegalArgumentException {
        return new VoidBlockState(mirror.getBlockState(((VoidNamedKey) key).getCosmicReachId()));
    }

    @Override
    public Collection<BlockState> getAllBlockStates() {
        final List<BlockState> collector = new ArrayList<>();
        for (final finalforeach.cosmicreach.blocks.BlockState value : mirror.blockStates.values()) {
            collector.add(new VoidBlockState(value));
        }
        return collector;
    }

    @Override
    public BlockState getDefaultBlockState() {
        return new VoidBlockState(mirror.getDefaultBlockState());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final VoidBlockType that)) return false;
        return Objects.equals(mirror, that.mirror);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mirror);
    }

    @Override
    public String toString() {
        return this.key.toString();
    }
}
