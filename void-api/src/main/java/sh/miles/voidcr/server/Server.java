package sh.miles.voidcr.server;

import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.server.configuration.ServerConfiguration;

import java.nio.file.Path;

/**
 * The Cosmic Reach Server.
 *
 * @since 0.3.14
 */
@NullMarked
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
     * Gets the folder of the server
     *
     * @return the server folder
     * @since 0.3.14
     */
    Path getServerFolder();
}
