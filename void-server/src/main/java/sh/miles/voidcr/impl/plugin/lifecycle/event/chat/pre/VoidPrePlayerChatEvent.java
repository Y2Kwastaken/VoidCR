package sh.miles.voidcr.impl.plugin.lifecycle.event.chat.pre;

import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.server.ServerIdentity;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.impl.entity.VoidPlayerEntity;
import sh.miles.voidcr.impl.plugin.lifecycle.event.chat.VoidPlayerChatEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.chat.pre.PrePlayerChatEvent;
import sh.miles.voidcr.server.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VoidPrePlayerChatEvent extends VoidPlayerChatEvent implements PrePlayerChatEvent {
    private boolean canceled = false;
    private List<PlayerEntity> recipients;

    public VoidPrePlayerChatEvent(final Server context, final String message, final Player player) {
        super(context, message, player);
        this.recipients = new ArrayList<>();
        for (final ServerIdentity authenticatedConnection : ServerSingletons.SERVER.authenticatedConnections) {
            recipients.add((VoidPlayerEntity) authenticatedConnection.getPlayer().getEntity().getVoidMirror());
        }
    }

    @Override
    public void setMessage(final String message) {
        super.message = message;
    }

    @Override
    public void setRecipients(final PlayerEntity... recipients) {
        this.recipients.clear();
        for (final PlayerEntity recipient : recipients) {
            this.recipients.add(recipient);
        }
    }

    @Override
    public Collection<PlayerEntity> getRecipients() {
        return this.recipients;
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
        return PrePlayerChatEvent.class;
    }

    public Array<ServerIdentity> getCosmicReachRecipients() {
        final Array<ServerIdentity> collector = new Array<>(this.recipients.size());
        for (final PlayerEntity recipient : this.recipients) {
            collector.add(ServerSingletons.getConnection(((VoidPlayerEntity) recipient).getMirror().player));
        }

        return collector;
    }
}
