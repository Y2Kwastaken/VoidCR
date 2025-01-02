package sh.miles.voidcr.plugin.type;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import sh.miles.voidcr.server.Server;

/**
 * A standard plugin loads after the world has fully loaded and all features are accessible.
 *
 * @since 1.21
 */
@ApiStatus.OverrideOnly
@ApiStatus.AvailableSince("0.3.11")
public interface StandardPlugin {

    /**
     * Initializes the standard plugin.
     *
     * @param server the server instance at time of initialization
     * @since 1.21
     */
    @ApiStatus.AvailableSince("0.3.11")
    void initialize(@NotNull Server server);

    /**
     * Disables the standard plugin.
     *
     * @param server the server instance at time of disabling
     * @since 1.21
     */
    @ApiStatus.AvailableSince("0.3.11")
    void disable(@NotNull Server server);
}
