package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.impl.world.position.VoidBlockPos;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.PlayerPlaceBlockEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.position.BlockPos;

public abstract class VoidPlayerPlaceBlockEvent extends VoidBlockEvent implements PlayerPlaceBlockEvent {

    private final PlayerEntity player;
    private final BlockPos pos;

    public VoidPlayerPlaceBlockEvent(final Server context, final Player player, final BlockPosition position, final BlockState state) {
        super(context, player.getZone(), state);
        this.player = (PlayerEntity) player.getEntity().getVoidMirror();
        this.pos = VoidBlockPos.fromCRPos(position);

    }

    @Override
    public PlayerEntity getPlayer() {
        return this.player;
    }

    @Override
    public BlockPos getBlockPos() {
        return this.pos;
    }
}
