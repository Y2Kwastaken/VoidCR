package sh.miles.voidcr.plugin.lifecycle.event.world.pre;

import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.UniverseTickEndEvent;
import sh.miles.voidcr.server.Server;

/**
 * An event that occurs that before a tick ends, unlike most events this event has no corresponding post event
 * <p>
 * Warning this event has an extremely high call rate, it is not recommended to run anything, but efficient and fast
 * executing code within it as it could heavily impact the tick loop more than calling this event already does
 *
 * @since 0.3.27
 */
public interface PreUniverseTickEndEvent extends UniverseTickEndEvent, LifecycleEvent<Server> {
}
