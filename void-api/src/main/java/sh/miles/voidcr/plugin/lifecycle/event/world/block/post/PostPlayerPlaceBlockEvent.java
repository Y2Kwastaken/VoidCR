package sh.miles.voidcr.plugin.lifecycle.event.world.block.post;

import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.PlayerPlaceBlockEvent;
import sh.miles.voidcr.server.Server;

/**
 * Event that occurs after a player places a block
 *
 * @since 0.3.26
 */
public interface PostPlayerPlaceBlockEvent extends PlayerPlaceBlockEvent, LifecycleEvent<Server> {
}
