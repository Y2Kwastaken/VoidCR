package sh.miles.voidcr.plugin.lifecycle.event.entity.post;

import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.entity.EntityDamageEvent;
import sh.miles.voidcr.plugin.lifecycle.event.entity.pre.PreEntityDamageEvent;
import sh.miles.voidcr.server.Server;

/**
 * An event that takes place after the finalization of damage and the {@link PreEntityDamageEvent}
 *
 * @since 0.3.23
 */
public interface PostEntityDamageEvent extends EntityDamageEvent, LifecycleEvent<Server> {
}
