package sh.miles.voidcr.impl.entity;

import com.badlogic.gdx.math.Vector3;
import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.networking.packets.entities.EntityPositionPacket;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.impl.world.VoidWorld;
import sh.miles.voidcr.impl.world.position.VoidPosition;
import sh.miles.voidcr.impl.world.position.VoidVector;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.world.World;
import sh.miles.voidcr.world.position.BlockPos;
import sh.miles.voidcr.world.position.Position;
import sh.miles.voidcr.world.position.Vector;

import java.util.Objects;

public class VoidEntity implements Entity, Mirrored<finalforeach.cosmicreach.entities.Entity> {

    protected final finalforeach.cosmicreach.entities.Entity mirror;
    protected final EntityIdentifier cachedIdentifier;

    public VoidEntity(finalforeach.cosmicreach.entities.Entity mirror) {
        this.mirror = mirror;
        this.cachedIdentifier = new VoidEntityIdentifier(mirror.uniqueId);
    }

    @Override
    public void teleport(final Position to) {
        teleport(getWorld(), to);
    }

    @Override
    public void teleport(final World world, final Position to) {
        Preconditions.checkArgument(world != null, "The provided world must not be null");
        Preconditions.checkArgument(to != null, "The provided to position must not be null");

        mirror.position = new Vector3(to.x(), to.y(), to.z());
        ServerSingletons.SERVER.broadcast(((VoidWorld) world).getMirror(), new EntityPositionPacket(this.getMirror()));
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
    public World getWorld() {
        return mirror.zone.getVoidMirror();
    }

    @Override
    public Position getPosition() {
        return VoidPosition.fromVector3(mirror.position);
    }

    @Override
    public Vector getVelocity() {
        return VoidVector.fromVector3(mirror.velocity);
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
