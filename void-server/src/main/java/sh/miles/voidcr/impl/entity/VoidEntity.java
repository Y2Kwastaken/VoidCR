package sh.miles.voidcr.impl.entity;

import com.badlogic.gdx.math.Vector3;
import com.google.common.base.Preconditions;
import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.impl.world.position.VoidPosition;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.world.position.Position;

import java.util.Objects;

public class VoidEntity implements Entity, Mirrored<finalforeach.cosmicreach.entities.Entity> {

    protected final finalforeach.cosmicreach.entities.Entity mirror;
    protected final EntityIdentifier cachedIdentifier;

    public VoidEntity(finalforeach.cosmicreach.entities.Entity mirror) {
        this.mirror = mirror;
        this.cachedIdentifier = new VoidEntityIdentifier(mirror.uniqueId);
    }

    @Override
    public int getMaximumInvulnerabilityFrames() {
        return mirror.maxInvulnerabilityFrames;
    }

    @Override
    public void setMaximumInvulnerabilityFrames(final int frames) {
        Preconditions.checkArgument(frames >= 0, "The provided amount of invulnerability frames must be greater than or equal to 0");
        mirror.maxInvulnerabilityFrames = frames;
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
    public void setHealth(final float health) {
        Preconditions.checkArgument(health >= 0, "The provided health value must be greater than or equal to 0");
        mirror.hitpoints = health;
    }

    @Override
    public float getMaxHealth() {
        return mirror.maxHitpoints;
    }

    @Override
    public void setMaxHealth(final float health) {
        Preconditions.checkArgument(health > 0, "The provided maximum health must be greater than 0");
        mirror.maxHitpoints = health;
    }

    @Override
    public Position getPosition() {
        return VoidPosition.fromVector3(mirror.position);
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
