package sh.miles.voidcr.impl.world.block.entity;

import sh.miles.voidcr.world.block.entity.BlockEntityLaserEmitter;

public class VoidBlockEntityLaserEmitter extends VoidBlockEntity<finalforeach.cosmicreach.blockentities.BlockEntityLaserEmitter> implements BlockEntityLaserEmitter {
    public VoidBlockEntityLaserEmitter(final finalforeach.cosmicreach.blockentities.BlockEntityLaserEmitter mirror) {
        super(mirror);
    }

    @Override
    public void shoot() {
        mirror.shootProjectile();
    }

    @Override
    public void shootNow() {
        mirror.shootProjectileNow();
    }

    @Override
    public boolean willShootNextTick() {
        return mirror.willShoot;
    }
}
