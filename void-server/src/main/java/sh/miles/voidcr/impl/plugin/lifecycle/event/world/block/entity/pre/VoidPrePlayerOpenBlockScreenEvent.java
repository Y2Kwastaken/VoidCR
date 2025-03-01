package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.pre;

import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.VoidPlayerOpenBlockScreenEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.pre.PrePlayerOpenBlockScreenEvent;
import sh.miles.voidcr.server.Server;

public final class VoidPrePlayerOpenBlockScreenEvent extends VoidPlayerOpenBlockScreenEvent implements PrePlayerOpenBlockScreenEvent {

    private boolean canceled = false;

    public VoidPrePlayerOpenBlockScreenEvent(final Server context, final Player player, final BlockEntity entity) {
        super(context, player, entity);
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
        return PrePlayerOpenBlockScreenEvent.class;
    }
}
