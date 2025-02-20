package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.pre;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.VoidPlayerInteractBlockEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.pre.PrePlayerInteractBlockEvent;
import sh.miles.voidcr.server.Server;

public class VoidPrePlayerInteractBlockEvent extends VoidPlayerInteractBlockEvent implements PrePlayerInteractBlockEvent {

    private boolean canceled = false;

    public VoidPrePlayerInteractBlockEvent(final Server context, final Player player, final BlockPosition position, final BlockState state) {
        super(context, player, position, state);
    }

    @Override
    public void setCanceled(final boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public boolean isCanceled() {
        return this.canceled;
    }

    @Override
    public Class<? extends LifecycleEvent<Server>> getEventClass() {
        return PrePlayerInteractBlockEvent.class;
    }
}
