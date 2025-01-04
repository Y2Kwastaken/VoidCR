package sh.miles.voidcr.plugin.type;

import sh.miles.voidcr.server.Server;

/**
 * A standard plugin loads after the world has fully loaded and all features are accessible.
 *
 * @since 0.3.14
 */
public interface StandardPlugin {

    /**
     * Initializes the standard plugin.
     *
     * @param server the server instance at time of initialization
     * @since 0.3.14
     */
    void initialize(Server server);

    /**
     * Disables the standard plugin.
     *
     * @param server the server instance at time of disabling
     * @since 0.3.14
     */
    void disable(Server server);
}
