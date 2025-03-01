package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.post;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.VoidPlayerBreakBlockEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.post.PostPlayerBreakBlockEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.post.PostPlayerPlaceBlockEvent;
import sh.miles.voidcr.server.Server;

public final class VoidPostPlayerBreakBlockEvent extends VoidPlayerBreakBlockEvent implements PostPlayerBreakBlockEvent {
    public VoidPostPlayerBreakBlockEvent(final Server context, final Player player, final BlockPosition position, final BlockState state) {
        super(context, player, position, state);
    }

    @Override
    public Class<? extends LifecycleEvent<Server>> getEventClass() {
        return PostPlayerPlaceBlockEvent.class;
    }
}
