package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.impl.entity.VoidPlayerEntity;
import sh.miles.voidcr.impl.world.position.VoidBlockPos;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.PlayerBreakBlockEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.position.BlockPos;

public abstract class VoidPlayerBreakBlockEvent extends VoidBlockEvent implements PlayerBreakBlockEvent {

    private final PlayerEntity player;
    private final BlockPos pos;

    public VoidPlayerBreakBlockEvent(final Server context, final Player player, final BlockPosition position, final BlockState state) {
        super(context, position.getZone(), state);
        this.player = (VoidPlayerEntity) player.getEntity().getVoidMirror();
        this.pos = VoidBlockPos.fromCRPos(position);
    }

    @Override
    public BlockPos getBlockPos() {
        return this.pos;
    }

    @Override
    public PlayerEntity getPlayer() {
        return this.player;
    }
}
