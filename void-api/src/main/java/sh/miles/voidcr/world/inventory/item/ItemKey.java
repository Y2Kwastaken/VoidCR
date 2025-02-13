package sh.miles.voidcr.world.inventory.item;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.server.VoidCR;
import sh.miles.voidcr.util.NamedKey;

/**
 * A more permissible {@link NamedKey} that holds extra state information
 *
 * @since 0.3.23
 */
public interface ItemKey extends NamedKey {

    /**
     * Gets a state value from a state key.
     * <p>
     * State keys are usually generated off of certain criteria, e.g. stair_type or slab_type. This method is closely
     * linked to the underlying data format used by data mods so the results this method provides may be unstable.
     *
     * @param stateKey the stateKey
     * @return the state value, or null
     * @since 0.3.23
     */
    @Nullable
    String getStateValue(String stateKey);

    /**
     * Checks if this ItemKey has a state key
     * <p>
     * State keys are usually generated off of certain criteria, e.g. stair_type or slab_type. This method is closely
     * linked to the underlying data format used by data mods so the results this method provides may be unstable.
     *
     * @param stateKey the stateKey to check for
     * @return true if this item key has the provided state key, otherwise false
     * @since 0.3.23
     */
    boolean hasStateValue(String stateKey);

    /**
     * Creates a {@link ItemKey} from a provided namespace and key value.
     * <p>
     * The namespace should not have anything that is not a letter, hyphen or underscore. [a-zA-Z_-]
     * <p>
     * The key must not violate th is regex [a-zA-Z_-\[\]\=\,]
     *
     * @param namespace the namespace
     * @param key       the key
     * @return a ItemKey
     * @throws IllegalArgumentException thrown if the parameters violates the naming restrictions
     * @since 0.3.23
     */
    static ItemKey key(final String namespace, final String key) throws IllegalArgumentException {
        return VoidCR.getMagic().createItemKey(namespace, key);
    }

    /**
     * Creates a {@link ItemKey} from a provided namespace and key value.
     * <p>
     * The namespace should not have anything that is not a letter, hyphen or underscore. [a-zA-Z_-]
     * <p>
     * The key must not violate th is regex [a-zA-Z_-\[\]\=\,]
     *
     * @param keyString the keyString of format "namespace:key[data=data]" or "namespace:key"
     * @return the ItemKey
     * @throws IllegalArgumentException thrown if the parameter violates the naming restrictions
     * @since 0.3.23
     */
    static ItemKey key(final String keyString) throws IllegalArgumentException {
        return VoidCR.getMagic().createItemKey(keyString);
    }

    /**
     * Creates a {@link ItemKey} in Cosmic Reach's namespace of the given key
     * <p>
     * The namespace should not have anything that is not a letter, hyphen or underscore. [a-zA-Z_-]
     * <p>
     * The key must not violate th is regex [a-zA-Z_-\[\]\=\,]
     *
     * @param key the key of the {@link ItemKey}
     * @return the ItemKey
     * @throws IllegalArgumentException thrown if the parameter violates the naming restrictions
     * @since 0.3.23
     */
    static ItemKey cosmicReach(final String key) throws IllegalArgumentException {
        return VoidCR.getMagic().createItemKey(COSMIC_REACH, key);
    }
}
