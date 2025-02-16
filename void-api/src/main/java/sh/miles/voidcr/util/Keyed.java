package sh.miles.voidcr.util;

/**
 * Represents an object that is attached to a known key
 *
 * @since 0.3.14
 */
public interface Keyed {
    /**
     * Get the key of this object
     *
     * @return the key
     * @since 0.3.14
     */
    NamedKey key();
}
