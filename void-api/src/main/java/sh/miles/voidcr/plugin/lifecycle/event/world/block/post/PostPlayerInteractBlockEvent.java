package sh.miles.voidcr.plugin.lifecycle.event.world.block.post;

import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.PlayerInteractBlockEvent;
import sh.miles.voidcr.server.Server;

/**
 * An event that occurs after a player interacts with a block
 *
 * @since 0.3.27
 */
public interface PostPlayerInteractBlockEvent extends PlayerInteractBlockEvent, LifecycleEvent<Server> {
}
