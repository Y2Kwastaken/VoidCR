package sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.post;

import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.PlayerSignUpdateEvent;
import sh.miles.voidcr.server.Server;

/**
 * Event that takes place after a sign has been updated
 *
 * @since 0.3.26
 */
public interface PostPlayerSignUpdateEvent extends PlayerSignUpdateEvent, LifecycleEvent<Server> {
}
