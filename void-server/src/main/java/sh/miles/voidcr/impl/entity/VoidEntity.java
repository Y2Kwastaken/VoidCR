package sh.miles.voidcr.impl.entity;

import com.badlogic.gdx.math.Vector3;
import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.util.Mirrored;

import java.util.Objects;

public class VoidEntity implements Entity, Mirrored<finalforeach.cosmicreach.entities.Entity> {

    private final finalforeach.cosmicreach.entities.Entity mirror;
    private final EntityIdentifier cachedIdentifier;

    public VoidEntity(finalforeach.cosmicreach.entities.Entity mirror) {
        this.mirror = mirror;
        this.cachedIdentifier = new VoidEntityIdentifier(mirror.uniqueId);
    }

    @Override
    public float getRangeOfSight() {
        return mirror.sightRange;
    }

    @Override
    public float getHealth() {
        return mirror.hitpoints;
    }

    @Override
    public float getMaxHealth() {
        return mirror.maxHitpoints;
    }

    @Override
    public Vector3 getPosition() {
        return mirror.position.cpy();
    }

    @Override
    public Vector3 getVelocity() {
        return mirror.velocity.cpy();
    }

    @Override
    public EntityIdentifier getIdentifier() {
        return this.cachedIdentifier;
    }

    @Override
    public finalforeach.cosmicreach.entities.Entity getMirror() {
        return this.mirror;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final VoidEntity that)) return false;
        return Objects.equals(mirror, that.mirror);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mirror);
    }
}
