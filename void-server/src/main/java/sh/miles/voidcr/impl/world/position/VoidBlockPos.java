package sh.miles.voidcr.impl.world.position;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import sh.miles.voidcr.impl.util.VoidMagicMethods;
import sh.miles.voidcr.util.CRSerializerHelper;
import sh.miles.voidcr.util.CRSerializerHelper.CRBinSerializerWrapper;
import sh.miles.voidcr.world.Chunk;
import sh.miles.voidcr.world.position.BlockPos;
import sh.miles.voidcr.world.position.LocalBlockPos;

public class VoidBlockPos extends VoidIntPosition<BlockPos> implements BlockPos {

    public static VoidBlockPos fromCRBlockEntity(BlockEntity blockEntity) {
        return new VoidBlockPos(blockEntity.getGlobalX(), blockEntity.getGlobalY(), blockEntity.getGlobalZ());
    }

    public static VoidBlockPos fromCRPos(BlockPosition position) {
        return new VoidBlockPos(position.getGlobalX(), position.getGlobalY(), position.getGlobalZ());
    }

    public VoidBlockPos(final int x, final int y, final int z) {
        super(x, y, z);
    }

    @Override
    public BlockPos create(final int x, final int y, final int z, final Object... other) {
        return new VoidBlockPos(x, y, z);
    }

    @Override
    protected Object[] others() {
        return new Object[0];
    }

    @Override
    public LocalBlockPos bindTo(final Chunk chunk, final boolean truncate) throws IllegalStateException {
        if (truncate) {
            return new VoidLocalBlockPos(x >> 4, y >> 4, z >> 4, chunk);
        }
        Preconditions.checkState(x < 16 && y < 16 && z < 16, "The current BlockPos (%d, %d, %d) values must be truncated to be bound to a chunk".formatted(x, y, z));
        return new VoidLocalBlockPos(x, y, z, chunk);
    }

    @Override
    public LocalBlockPos bindTo(final Chunk chunk) throws IllegalStateException {
        return bindTo(chunk, false);
    }

    @Override
    public byte[] toBytes() {
        final CRBinSerializerWrapper<BlockPos> wrapped = CRSerializerHelper.create(BlockPos.class);
        wrapped.setResult(this);
        return VoidMagicMethods.serialize(wrapped);
    }

    @Override
    public String toString() {
        return "VoidBlockPos{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public static VoidBlockPos deserialize(ICRBinSerializable serializable) {
        if (serializable instanceof CRBinSerializerWrapper<?> wrapper) {
            return (VoidBlockPos) wrapper.getResult();
        }

        throw new IllegalArgumentException("Unexpected serializable class " + serializable.getClass());
    }
}
