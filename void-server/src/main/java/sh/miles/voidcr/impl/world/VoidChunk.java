package sh.miles.voidcr.impl.world;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.world.BlockSetter;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.impl.world.block.VoidBlockState;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.world.Chunk;
import sh.miles.voidcr.world.block.BlockState;
import sh.miles.voidcr.world.block.entity.BlockEntity;
import sh.miles.voidcr.world.position.BlockPos;

import java.util.Collection;
import java.util.List;

public class VoidChunk implements Chunk, Mirrored<finalforeach.cosmicreach.world.Chunk> {

    private final finalforeach.cosmicreach.world.Chunk mirror;

    public VoidChunk(finalforeach.cosmicreach.world.Chunk mirror) {
        this.mirror = mirror;
    }

    @Override
    public int chunkX() {
        return mirror.chunkX;
    }

    @Override
    public int chunkY() {
        return mirror.chunkY;
    }

    @Override
    public int chunkZ() {
        return mirror.chunkZ;
    }

    @Override
    public BlockEntity getBlockEntity(final int x, final int y, final int z) {
        verifyExplicitPosition(x, y, z);
        final var entity = mirror.getBlockEntity(x, y, z);
        return entity == null ? null : entity.getVoidMirror();
    }

    @Override
    public @Nullable BlockEntity getBlockEntity(final BlockPos pos) {
        verifyPosition(pos);
        final var entity = mirror.getBlockEntity(pos.x(), pos.y(), pos.z());
        return entity == null ? null : entity.getVoidMirror();
    }

    @Override
    public void setBlockState(final int x, final int y, final int z, final BlockState state) {
        verifyExplicitPosition(x, y, z);
        BlockSetter.get().replaceBlock(((VoidBlockState) state).getMirror(), new BlockPosition(this.mirror, x, y, z));
    }

    @Override
    public BlockState getBlockState(final int x, final int y, final int z) {
        verifyExplicitPosition(x, y, z);
        return mirror.getBlockState(x, y, z).getVoidMirror();
    }

    @Override
    public void setBlockState(final BlockPos pos, final BlockState state) {
        verifyPosition(pos);
        BlockSetter.get().replaceBlock(((VoidBlockState) state).getMirror(), new BlockPosition(this.mirror, pos.x(), pos.y(), pos.z()));
    }

    @Override
    public BlockState getBlockState(final BlockPos pos) {
        verifyPosition(pos);
        return mirror.getBlockState(pos.x(), pos.y(), pos.z()).getVoidMirror();
    }

    @Override
    public Collection<BlockEntity> getAllBlockEntities() {
        return mirror.blockEntities == null ? List.of() : mirror.blockEntities.values().stream().map((b) -> (BlockEntity) b.getVoidMirror()).toList();
    }

    @Override
    public boolean hasBlockEntities() {
        return mirror.hasBlockEntities();
    }

    @Override
    public finalforeach.cosmicreach.world.Chunk getMirror() {
        return this.mirror;
    }

    private void verifyExplicitPosition(int x, int y, int z) {
        Preconditions.checkArgument((x > 0 && y > 0 && z > 0) && (x < 16 && y < 16 && z < 16), "the provided values must all be between 0 inclusively, and 16 exclusively or (0, 16]");
    }

    private void verifyPosition(BlockPos pos) {
        Preconditions.checkArgument(pos != null, "The provided block position must not be null");
        Preconditions.checkArgument(
                (pos.x() >= 0 && pos.x() < 16) &&
                        (pos.y() >= 0 && pos.y() < 16) &&
                        (pos.z() >= 0 && pos.z() < 16),
                "All values in a block position provided in a chunk method must be bounded between 0 and 15 inclusively [0, 15]"
        );
    }
}
