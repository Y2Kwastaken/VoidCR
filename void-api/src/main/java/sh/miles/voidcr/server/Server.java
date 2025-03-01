package sh.miles.voidcr.server;

import org.apache.logging.log4j.Logger;
import sh.miles.voidcr.plugin.lifecycle.LifecycleManager;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.server.configuration.ServerConfiguration;
import sh.miles.voidcr.util.MagicMethods;

import java.nio.file.Path;

/**
 * The Cosmic Reach Server.
 *
 * @since 0.3.14
 */
public interface Server {

    /**
     * Gets the server's main configuration file
     *
     * @return the server configuration
     * @since 0.3.14
     */
    ServerConfiguration getConfiguration();

    /**
     * Gets the server's logger
     *
     * @return the logger
     * @since 0.3.14
     */
    Logger getLogger();

    /**
     * Gets the lifecycle manager for the server lifecycle
     *
     * @return the server lifecycle
     * @since 0.3.22
     */
    LifecycleManager<Server> getLifecycle();

    /**
     * Gets the folder of the server
     *
     * @return the server folder
     * @since 0.3.14
     */
    Path getServerFolder();

    /**
     * Gets the server console
     *
     * @return the console
     * @since 0.3.27
     */
    Console getConsole();

    /**
     * Gets the magic methods class see {@link MagicMethods} on why you should probably not be accessing this class.
     *
     * @return magic methods
     * @since 0.3.14
     * @deprecated do not use this in standard API unless absolutely necessary. Maybe suggest a PR or open an issue?
     */
    @Deprecated
    MagicMethods getMagic();
}
