package sh.miles.voidcr.impl.plugin.lifecycle.event.chat.post;

import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.impl.plugin.lifecycle.event.chat.VoidPlayerChatEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.chat.post.PostPlayerChatEvent;
import sh.miles.voidcr.server.Server;

public class VoidPostPlayerChatEvent extends VoidPlayerChatEvent implements PostPlayerChatEvent {

    public VoidPostPlayerChatEvent(final Server context, final String message, final Player player) {
        super(context, message, player);
    }

    @Override
    public Class<? extends LifecycleEvent<Server>> getEventClass() {
        return PostPlayerChatEvent.class;
    }
}
