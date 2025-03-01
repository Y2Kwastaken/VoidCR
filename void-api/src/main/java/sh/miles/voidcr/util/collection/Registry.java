package sh.miles.voidcr.util.collection;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.util.collection.exception.RegistryKeyClashException;
import sh.miles.voidcr.util.collection.exception.RegistryValueNotFoundException;

/**
 * A registry which could, potentially be modified and is used to store items.
 *
 * @param <E> the registry value type
 * @since 0.3.14
 */
public interface Registry<E extends KeyHolder<K>, K> extends Iterable<E> {

    /**
     * Attempts to fetch a value of the given key from the registry
     *
     * @param key the key to fetch
     * @return the value from the registry if present, or null
     * @since 0.3.14
     */
    @Nullable
    E get(K key);

    /**
     * Attempts to fetch a value of the given key from the registry. If the value isn't present an exception is thrown
     *
     * @param key the key to get from the registry
     * @return the value at that key
     * @throws RegistryValueNotFoundException thrown if no value is present at the key
     * @since 0.3.17
     */
    E getOrThrow(K key) throws RegistryValueNotFoundException;

    /**
     * Registers a value with this registry
     *
     * @param value the value to register
     * @throws RegistryKeyClashException thrown if their is already a value with the inserted key present
     * @since 0.3.27
     */
    void register(E value) throws RegistryKeyClashException;

    /**
     * Checks whether or not this registry is frozen. When a registry is frozen it can not be changed and only queried.
     *
     * @return true if the registry is frozen.
     * @since 0.3.14
     */
    boolean isFrozen();
}

