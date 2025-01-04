package sh.miles.voidcr.server.configuration;

import java.nio.file.Path;

/**
 * Represents the standard server configuration file for VoidCR, aka the void.json
 */
public interface ServerConfiguration {
    Path pluginsDirectory();
}
