package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.pre;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.VoidPlayerBreakBlockEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.pre.PrePlayerBreakBlockEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.block.BlockState;

public class VoidPrePlayerBreakBlockEvent extends VoidPlayerBreakBlockEvent implements PrePlayerBreakBlockEvent {

    private boolean canceled = false;

    public VoidPrePlayerBreakBlockEvent(final Server context, final Player player, final BlockPosition position, final finalforeach.cosmicreach.blocks.BlockState state) {
        super(context, player, position, state);
    }

    @Override
    public void setBlockState(final BlockState state) {
        super.state = state;
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
        return PrePlayerBreakBlockEvent.class;
    }
}
