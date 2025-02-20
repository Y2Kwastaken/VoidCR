package sh.miles.voidcr.plugin.lifecycle.event.world.block.pre;

import sh.miles.voidcr.plugin.lifecycle.Cancelable;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.PlayerInteractBlockEvent;
import sh.miles.voidcr.server.Server;

/**
 * An event that occurs before a player interacts with a block
 *
 * @since 0.3.27
 */
public interface PrePlayerInteractBlockEvent extends PlayerInteractBlockEvent, Cancelable, LifecycleEvent<Server> {
}
