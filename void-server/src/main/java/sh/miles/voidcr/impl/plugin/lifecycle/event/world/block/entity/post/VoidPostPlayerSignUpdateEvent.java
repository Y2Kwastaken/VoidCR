package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.post;

import finalforeach.cosmicreach.blockentities.BlockEntitySign;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.VoidPlayerSignUpdateEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.server.Server;

public class VoidPostPlayerSignUpdateEvent extends VoidPlayerSignUpdateEvent {

    public VoidPostPlayerSignUpdateEvent(final Server context, final BlockEntitySign sign, Player player) {
        super(context, sign.getZone(), sign, sign.getText(), sign.getTextColor(), sign.getFontSize(), player);
    }

    @Override
    public Class<? extends LifecycleEvent<Server>> getEventClass() {
        return VoidPlayerSignUpdateEvent.class;
    }
}
