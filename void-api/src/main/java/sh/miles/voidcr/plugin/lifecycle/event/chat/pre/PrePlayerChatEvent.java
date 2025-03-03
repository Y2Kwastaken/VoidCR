package sh.miles.voidcr.plugin.lifecycle.event.chat.pre;

import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.plugin.lifecycle.Cancelable;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.chat.PlayerChatEvent;
import sh.miles.voidcr.server.Server;

import java.util.Collection;

/**
 * Event triggered before a message is sent to recipients
 *
 * @since 0.3.27
 */
public interface PrePlayerChatEvent extends PlayerChatEvent, Cancelable, LifecycleEvent<Server> {

    /**
     * Modifies the message sent by the player
     *
     * @param message the message sent
     * @since 0.3.27
     */
    void setMessage(String message);

    /**
     * Restricts the recipients of this message to the provided recipients
     *
     * @param recipients the recipients
     * @since 0.3.27
     */
    void setRecipients(PlayerEntity... recipients);

    /**
     * Gets all the recipients of this message
     *
     * @return the recipients
     * @since 0.3.27
     */
    Collection<PlayerEntity> getRecipients();
}
