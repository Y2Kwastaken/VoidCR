package sh.miles.voidcr.plugin.lifecycle.event.chat;

import sh.miles.voidcr.entity.PlayerEntity;

/**
 * Event called when a player chats
 *
 * @since 0.3.27
 */
public interface PlayerChatEvent {

    /**
     * Gets the message sent
     *
     * @return the message sent
     * @since 0.3.27
     */
    String getMessage();

    /**
     * Gets the player who chatted
     *
     * @return the player
     * @since 0.3.27
     */
    PlayerEntity getSender();
}
