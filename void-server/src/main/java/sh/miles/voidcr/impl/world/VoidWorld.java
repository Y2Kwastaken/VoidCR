package sh.miles.voidcr.impl.world;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.impl.entity.VoidEntity;
import sh.miles.voidcr.impl.entity.VoidEntityIdentifier;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.Universe;
import sh.miles.voidcr.world.World;

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
    public Vector3 getWorldSpawn() {
        return this.mirror.spawnPoint.cpy();
    }

    @Override
    public Entity getEntity(final EntityIdentifier identifier) {
        return this.mirror.getEntity(((VoidEntityIdentifier) identifier).getMirror()).getVoidMirror();
    }

    @Override
    public void removeEntity(final Entity entity) {
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
