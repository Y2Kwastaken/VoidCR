package sh.miles.voidcr.impl.world.block;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.blocks.Block;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.impl.util.VoidNamedKey;
import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.block.BlockState;
import sh.miles.voidcr.world.block.BlockType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
    public BlockState getBlockState(final String parameterKey, final Object parameterValue) {
        final var state = mirror.getDefaultBlockState().getVariantWithParam(parameterKey, parameterValue);
        return state == null ? null : state.getVoidMirror();
    }

    @Override
    public @Nullable BlockState getBlockState(final Map<String, Object> parameters) {
        final var state = mirror.getDefaultBlockState().getVariantWithParams(parameters);
        return state == null ? null : state.getVoidMirror();
    }

    @Override
    public @Nullable BlockState getExactBlockState(final Map<String, Object> parameters) {
        final var state = mirror.blockStates.get(buildKey(this.key, parameters));
        return state == null ? null : state.getVoidMirror();
    }

    @Override
    public Collection<BlockState> getAllBlockStates() {
        final List<BlockState> collector = new ArrayList<>();
        for (final finalforeach.cosmicreach.blocks.BlockState value : mirror.blockStates.values()) {
            collector.add(value.getVoidMirror());
        }
        return collector;
    }

    @Override
    public BlockState getDefaultBlockState() {
        return mirror.getDefaultBlockState().getVoidMirror();
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

    private static String buildKey(NamedKey source, Map<String, Object> parameters) {
        Preconditions.checkArgument(source != null, "The provided source key must not be null");
        Preconditions.checkArgument(parameters != null, "The provided parameters must not be null");
        Preconditions.checkState(!parameters.isEmpty(), "The provided arguments must not be empty");

        final StringBuilder builder = new StringBuilder().append(source);
        builder.append("[");

        for (final Map.Entry<String, Object> entries : parameters.entrySet()) {
            builder.append(entries.getKey()).append('=').append(entries.getValue()).append(",");
        }

        builder.setCharAt(builder.length() - 1, ']');
        return builder.toString();
    }
}
