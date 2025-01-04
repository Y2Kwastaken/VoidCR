package sh.miles.voidcr.util;

/**
 * Internal exchange for values. This is an intermediary for methods that must be static. As the API is built entirely
 * around interfaces and isolation from internals sometimes these interchanges are needed. Seeing as they are
 * effectively internal it would be very smart not to use these in projects because as with internals they are subject
 * to change.
 *
 * @since 0.3.14
 * @deprecated this method should not be used within standard API use. Any of these methods are subject to removal at
 * any time.
 */
@Deprecated
public interface MagicMethods {

    /**
     * Deserializes the given cosmic reach class into bytes
     *
     * @param clazz the cosmic reach class
     * @param bytes the bytes to deserialize
     * @param <T>   the cosmic reach type
     * @return the deserialized content
     * @throws IllegalArgumentException if the class isn't a valid class or the bytes are not valid for that class
     * @since 0.3.14
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes) throws IllegalArgumentException;

    /**
     * Delegation for NamedKey static method
     *
     * @param namespace the namespace
     * @param key       the key
     * @return the named key
     * @throws IllegalArgumentException thrown if entries don't pass tests
     * @since 0.3.14
     */
    NamedKey createNamedKey(String namespace, String key) throws IllegalArgumentException;

    /**
     * Delegation for NamedKey static method
     *
     * @param keyString the keyString to turn into a NamedKey
     * @return the named key
     * @throws IllegalArgumentException thrown if parameter doesn't past test
     * @since 0.3.14
     */
    NamedKey createNamedKey(String keyString) throws IllegalArgumentException;
}
