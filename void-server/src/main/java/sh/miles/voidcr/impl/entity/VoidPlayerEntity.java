package sh.miles.voidcr.impl.entity;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.world.World;

public final class VoidPlayerEntity extends VoidEntity implements PlayerEntity {

    public VoidPlayerEntity(finalforeach.cosmicreach.entities.player.PlayerEntity mirror) {
        super(mirror);
        Preconditions.checkState(player() != null, "Every VoidPlayerEntity must have a player associated with it");
    }

    @Override
    public void respawn() {
        player().respawn(player().getZone());
    }

    @Override
    public World getWorld() {
        return player().getZone().getVoidMirror();
    }

    @Override
    public boolean isSprinting() {
        return player().isSprinting;
    }

    @Override
    public boolean isProne() {
        return player().isProne;
    }

    @Override
    public boolean isDead() {
        return player().isDead();
    }

    @Override
    public String getDisplayName() {
        return player().getUsername();
    }

    private Player player() {
        return ((finalforeach.cosmicreach.entities.player.PlayerEntity) super.mirror).player;
    }
}
