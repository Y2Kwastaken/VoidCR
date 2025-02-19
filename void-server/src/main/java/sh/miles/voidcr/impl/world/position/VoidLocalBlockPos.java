package sh.miles.voidcr.impl.world.position;

import com.google.common.base.Preconditions;
import sh.miles.voidcr.impl.world.VoidChunk;
import sh.miles.voidcr.world.Chunk;
import sh.miles.voidcr.world.position.BlockPos;
import sh.miles.voidcr.world.position.LocalBlockPos;

public class VoidLocalBlockPos extends VoidIntPosition<LocalBlockPos> implements LocalBlockPos {

    private final VoidChunk chunk;

    public VoidLocalBlockPos(final int x, final int y, final int z, Chunk chunk) {
        super(x, y, z);
        Preconditions.checkState(x < 16 && y < 16 && z < 16, "All x, y, and z values must be less than 16 to create a LocalBlockPos");
        Preconditions.checkArgument(chunk != null, "The provided chunk must not be null");
        this.chunk = (VoidChunk) chunk;
    }

    @Override
    public VoidLocalBlockPos create(final int x, final int y, final int z, final Object[] other) {
        return new VoidLocalBlockPos(x, y, z, (Chunk) other[0]);
    }

    @Override
    protected Object[] others() {
        return new Object[]{this.chunk};
    }

    @Override
    public Chunk chunk() {
        return this.chunk;
    }

    @Override
    public BlockPos unbind(final boolean expand) {
        return expand ? new VoidBlockPos(this.x >> 4, this.y >> 4, this.z >> 4) : new VoidBlockPos(this.x, this.y, this.z);
    }

    @Override
    public BlockPos unbind() {
        return unbind(false);
    }

    @Override
    public String toString() {
        return "VoidLocalBlockPos{" +
                "chunk=" + chunk +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
