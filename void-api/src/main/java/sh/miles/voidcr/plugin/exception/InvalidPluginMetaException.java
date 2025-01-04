package sh.miles.voidcr.plugin.exception;

import java.nio.file.Path;

/**
 * An exception thrown when a plugin has an invalid or malformed PluginMeta
 *
 * @since 0.3.14
 */
public class InvalidPluginMetaException extends IllegalStateException {

    public InvalidPluginMetaException(final Path file, final String invalidOrMissingField) {
        super("Unable to load plugin from jar because of invalid or missing field %s within the meta file %s".formatted(invalidOrMissingField, file.getFileName()));
    }

    public InvalidPluginMetaException(final String string) {
        super(string);
    }
}
