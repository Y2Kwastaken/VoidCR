package sh.miles.voidcr.impl.world;

import com.google.common.base.Preconditions;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.world.Chunk;
import sh.miles.voidcr.world.block.entity.BlockEntity;
import sh.miles.voidcr.world.position.LocalBlockPos;

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
        Preconditions.checkArgument((x > 0 && y > 0 && z > 0) && (x < 16 && y < 16 && z < 16), "the provided values must all be between 0 inclusively, and 16 exclusively or (0, 16]");
        final var entity = mirror.getBlockEntity(x, y, z);
        return entity == null ? null : entity.getVoidMirror();
    }

    @Override
    public @Nullable BlockEntity getBlockEntity(final LocalBlockPos pos) {
        Preconditions.checkArgument(pos != null, "The provided block pos must not be null");
        final var entity = mirror.getBlockEntity(pos.x(), pos.y(), pos.z());
        return entity == null ? null : entity.getVoidMirror();
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
}
