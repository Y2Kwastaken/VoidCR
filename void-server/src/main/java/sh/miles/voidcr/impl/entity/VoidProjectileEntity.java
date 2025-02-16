package sh.miles.voidcr.impl.entity;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.entities.EntityLaserProjectile;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.entity.ProjectileEntity;

public class VoidProjectileEntity extends VoidEntity implements ProjectileEntity {

    public VoidProjectileEntity(final EntityLaserProjectile mirror) {
        super(mirror);
    }

    @Override
    public void setStrength(final float strength) {
        Preconditions.checkArgument(strength >= 0, "The provided strength must be greater than or equal to zero");
        getMirror().strength = strength;
    }

    @Override
    public float getStrength() {
        return getMirror().strength;
    }

    @Override
    public float getRadius() {
        return getMirror().radius;
    }

    @Override
    public @Nullable EntityIdentifier getProjectileLauncher() {
        final var launcher = getMirror().sourceEntityId;
        return launcher == null ? null :  new VoidEntityIdentifier(launcher);
    }

    @Override
    public EntityLaserProjectile getMirror() {
        return (EntityLaserProjectile) super.mirror;
    }
}
