package sh.miles.voidcr.impl.world.block.entity;

import sh.miles.voidcr.impl.world.position.VoidBlockPos;
import sh.miles.voidcr.world.World;
import sh.miles.voidcr.world.block.entity.BlockEntity;
import sh.miles.voidcr.world.position.BlockPos;

public class VoidBlockEntity<T extends finalforeach.cosmicreach.blockentities.BlockEntity> implements BlockEntity {

    protected final T mirror;

    public VoidBlockEntity(T mirror) {
        this.mirror = mirror;
    }

    @Override
    public BlockPos getBlockPos() {
        return new VoidBlockPos(mirror.getGlobalX(), mirror.getGlobalY(), mirror.getGlobalZ());
    }

    @Override
    public World getWorld() {
        return mirror.getZone().getVoidMirror();
    }

    @Override
    public boolean isLoaded() {
        return mirror.loaded;
    }
}
