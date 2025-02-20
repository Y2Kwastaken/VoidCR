package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block;

import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.PlayerOpenBlockScreenEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.block.entity.BlockEntity;

public abstract class VoidPlayerOpenBlockScreenEvent extends VoidBlockEvent implements PlayerOpenBlockScreenEvent {

    private final sh.miles.voidcr.entity.PlayerEntity player;
    private final BlockEntity blockEntity;

    public VoidPlayerOpenBlockScreenEvent(final Server context, final Player player, final finalforeach.cosmicreach.blockentities.BlockEntity entity) {
        super(context, entity.getZone(), entity.getBlockState());
        this.blockEntity = getBlockEntity();
        this.player = (sh.miles.voidcr.entity.PlayerEntity) player.getEntity().getVoidMirror();
    }

    @Override
    public PlayerEntity getPlayer() {
        return this.player;
    }

    @Override
    public BlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
