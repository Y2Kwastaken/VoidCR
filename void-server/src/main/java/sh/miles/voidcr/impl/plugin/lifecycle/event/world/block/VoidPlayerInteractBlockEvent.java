package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.impl.world.position.VoidBlockPos;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.PlayerInteractBlockEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.position.BlockPos;

public abstract class VoidPlayerInteractBlockEvent extends VoidBlockEvent implements PlayerInteractBlockEvent {

    private final BlockPos position;
    private final PlayerEntity player;

    public VoidPlayerInteractBlockEvent(final Server context, final Player player, final BlockPosition position, final BlockState state) {
        super(context, player.getZone(), state);

        this.position = position == null ? null : VoidBlockPos.fromCRPos(position);
        this.player = (PlayerEntity) player.getEntity().getVoidMirror();
    }

    @Override
    public BlockPos getBlockPos() {
        return this.position;
    }

    @Override
    public PlayerEntity getPlayer() {
        return this.player;
    }
}
