package sh.miles.voidcr.plugin.lifecycle.event.chat.post;

import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.chat.PlayerChatEvent;
import sh.miles.voidcr.server.Server;

/**
 * Event triggered after a chat has been fully processed and sent
 *
 * @since 0.3.27
 */
public interface PostPlayerChatEvent extends PlayerChatEvent, LifecycleEvent<Server> {
}
