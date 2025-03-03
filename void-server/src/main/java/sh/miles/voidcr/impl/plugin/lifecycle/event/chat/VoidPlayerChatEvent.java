package sh.miles.voidcr.impl.plugin.lifecycle.event.chat;

import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.impl.entity.VoidPlayerEntity;
import sh.miles.voidcr.impl.plugin.lifecycle.event.VoidLifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.chat.PlayerChatEvent;
import sh.miles.voidcr.server.Server;

public abstract class VoidPlayerChatEvent extends VoidLifecycleEvent<Server> implements PlayerChatEvent {

    protected String message;
    protected final PlayerEntity player;

    public VoidPlayerChatEvent(final Server context, final String message, final Player player) {
        super(context);
        this.message = message;
        this.player = (VoidPlayerEntity) player.getEntity().getVoidMirror();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public PlayerEntity getSender() {
        return this.player;
    }
}
