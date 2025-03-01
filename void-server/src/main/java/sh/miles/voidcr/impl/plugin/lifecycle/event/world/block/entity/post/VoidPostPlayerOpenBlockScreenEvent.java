package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.post;

import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.VoidPlayerOpenBlockScreenEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.post.PostPlayerOpenBlockScreenEvent;
import sh.miles.voidcr.server.Server;

public final class VoidPostPlayerOpenBlockScreenEvent extends VoidPlayerOpenBlockScreenEvent implements PostPlayerOpenBlockScreenEvent {
    public VoidPostPlayerOpenBlockScreenEvent(final Server context, final Player player, final BlockEntity entity) {
        super(context, player, entity);
    }

    @Override
    public Class<? extends LifecycleEvent<Server>> getEventClass() {
        return PostPlayerOpenBlockScreenEvent.class;
    }
}
