package sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.pre;

import sh.miles.voidcr.plugin.lifecycle.Cancelable;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.PlayerOpenBlockScreenEvent;
import sh.miles.voidcr.server.Server;

/**
 * Event that occurs before a player opens a block screen
 *
 * @since 0.3.27
 */
public interface PrePlayerOpenBlockScreenEvent extends PlayerOpenBlockScreenEvent, Cancelable, LifecycleEvent<Server> {
}
