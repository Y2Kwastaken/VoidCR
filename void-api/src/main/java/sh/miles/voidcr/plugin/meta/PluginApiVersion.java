package sh.miles.voidcr.plugin.meta;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Defines Plugin Api Versions that plugins use.
 *
 * @since 0.3.11
 */
@ApiStatus.AvailableSince("0.3.11")
public interface PluginApiVersion {

    /**
     * Gets the plugin api version as a string.
     *
     * @return the plugin api version as a string
     * @since 0.3.11
     */
    @ApiStatus.AvailableSince("0.3.11")
    @NotNull
    String version();
}

