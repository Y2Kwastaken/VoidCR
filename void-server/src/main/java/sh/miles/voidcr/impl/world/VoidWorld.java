package sh.miles.voidcr.impl.world;

import com.badlogic.gdx.utils.Array;
import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.impl.entity.VoidEntity;
import sh.miles.voidcr.impl.entity.VoidEntityIdentifier;
import sh.miles.voidcr.impl.world.position.VoidBlockPos;
import sh.miles.voidcr.impl.world.position.VoidPosition;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.Chunk;
import sh.miles.voidcr.world.Universe;
import sh.miles.voidcr.world.World;
import sh.miles.voidcr.world.position.BlockPos;
import sh.miles.voidcr.world.position.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class VoidWorld implements World, Mirrored<Zone> {

    private final Zone mirror;
    private final NamedKey key;

    public VoidWorld(Zone mirror) {
        this.mirror = mirror;
        this.key = NamedKey.key(mirror.zoneId);
    }

    @Override
    public Zone getMirror() {
        return this.mirror;
    }

    @Override
    public Position getWorldSpawn() {
        return VoidPosition.fromVector3(mirror.spawnPoint);
    }

    @Override
    public Chunk getChunk(final int chunkX, final int chunkY, final int chunkZ) {
        final var chunk = mirror.getChunkAtChunkCoords(chunkX, chunkY, chunkZ);
        return chunk == null ? null : chunk.getVoidMirror();
    }

    @Override
    public Chunk getChunkAt(final BlockPos pos) {
        final var chunk = mirror.getChunkAtBlock(pos.x(), pos.y(), pos.z());
        return chunk == null ? null : chunk.getVoidMirror();
    }

    @Override
    public void setWorldSpawn(final Position spawnPoint) {
        Preconditions.checkArgument(spawnPoint != null, "The given spawn point must not be null");
        this.mirror.spawnPoint = VoidPosition.toVector3(spawnPoint);
    }

    @Override
    public Entity getEntity(final EntityIdentifier identifier) {
        Preconditions.checkArgument(identifier != null, "The given identifier must not be null");
        return this.mirror.getEntity(((VoidEntityIdentifier) identifier).getMirror()).getVoidMirror();
    }

    @Override
    public void removeEntity(final Entity entity) {
        Preconditions.checkArgument(entity != null, "The provided entity must not be null");
        this.mirror.removeEntity(((VoidEntity) entity).getMirror());
    }

    @Override
    public Collection<Entity> getEntities() {
        final Array<finalforeach.cosmicreach.entities.Entity> entities = this.mirror.getAllEntities();
        final List<Entity> collector = new ArrayList<>(entities.size);
        for (final finalforeach.cosmicreach.entities.Entity entity : entities) {
            collector.add(entity.getVoidMirror());
        }
        return collector;
    }

    @Override
    public Universe getUniverse() {
        return mirror.getWorld().getVoidMirror();
    }

    @Override
    public NamedKey getWorldId() {
        return this.key;
    }
}
