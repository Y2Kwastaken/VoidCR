package sh.miles.voidcr.server;

import org.apache.logging.log4j.Logger;
import sh.miles.voidcr.server.configuration.ServerConfiguration;
import sh.miles.voidcr.util.MagicMethods;

import java.nio.file.Path;

/**
 * Static wrapper class for server methods
 *
 * @since 0.3.14
 */
public final class VoidCR {

    private static Server server;

    private VoidCR() {
        throw new UnsupportedOperationException("Can not initialize VoidCR, which is a delegation class");
    }

    /**
     * @see Server#getConfiguration()
     */
    public static ServerConfiguration getConfiguration() {
        return server.getConfiguration();
    }

    /**
     * @see Server#getLogger()
     */
    public static Logger getLogger() {
        return server.getLogger();
    }

    /**
     * @see Server#getServerFolder()
     */
    public static Path getServerFolder() {
        return server.getServerFolder();
    }

    /**
     * @see Server#getMagic()
     */
    @Deprecated
    public static MagicMethods getMagic() {
        return server.getMagic();
    }

    /**
     * Internal
     *
     * @param server set server
     * @since 0.3.14
     */
    @Deprecated
    public static void setServer(final Server server) {
        VoidCR.server = server;
    }
}
