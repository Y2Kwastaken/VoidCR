package sh.miles.voidcr.util.collection;

/**
 * Represents an object which holds a key for a registry object
 *
 * @param <K> the key type
 * @since 0.3.27
 */
public interface KeyHolder<K> {
    /**
     * The key of this object
     *
     * @return the key
     * @since 0.3.27
     */
    K key();
}
