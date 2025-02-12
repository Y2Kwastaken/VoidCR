package sh.miles.voidcr.plugin.type;

import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.server.Server;

/**
 * A standard plugin loads after the world has fully loaded and all features are accessible.
 *
 * @since 0.3.14
 */
public interface StandardPlugin extends LifecycleAware<Server> {
}
