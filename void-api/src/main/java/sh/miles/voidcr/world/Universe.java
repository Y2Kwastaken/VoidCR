package sh.miles.voidcr.world;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.util.NamedKey;

import java.util.Collection;

/**
 * A Universe contains a collection of {@link World}'s and operations and contains basic information on a collection of
 * worlds.
 *
 * @since 0.3.14
 */
public interface Universe {

    /**
     * Sets the current time in the universe
     *
     * @param time the time to set, which must be greater than or equal to 0
     * @since 0.3.27
     */
    void setTime(long time);

    /**
     * Gets the time of the universe
     *
     * @return the time
     * @since 0.3.27
     */
    long getTime();

    /**
     * Gets a world from the given named key
     *
     * @param key the key
     * @return the world at that keys location ,or null
     */
    @Nullable
    World getWorld(NamedKey key);

    /**
     * Gets all worlds within this universe
     *
     * @return the worlds in this universe
     * @since 0.3.14
     */
    Collection<World> getWorlds();

    /**
     * Gets the universe's seed, which is used for world generation.
     *
     * @return the seed for this universe
     * @since 0.3.14
     */
    long getSeed();

    /**
     * Gets the named key of the default world
     *
     * @return the key of the default world
     */
    NamedKey getDefaultWorldKey();
}
