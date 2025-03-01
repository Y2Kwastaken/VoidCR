package sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.post;

import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.PlayerOpenBlockScreenEvent;
import sh.miles.voidcr.server.Server;

public interface PostPlayerOpenBlockScreenEvent extends PlayerOpenBlockScreenEvent, LifecycleEvent<Server> {
}
