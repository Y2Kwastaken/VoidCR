package sh.miles.voidcr.util;

import sh.miles.voidcr.server.registry.NamedRegistry;
import sh.miles.voidcr.util.collection.KeyHolder;
import sh.miles.voidcr.util.collection.Registry;
import sh.miles.voidcr.world.inventory.item.ItemKey;
import sh.miles.voidcr.world.inventory.item.ItemStack;
import sh.miles.voidcr.world.inventory.item.ItemType;
import sh.miles.voidcr.world.position.BlockPos;
import sh.miles.voidcr.world.position.Position;
import sh.miles.voidcr.world.position.Vector;

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
     * @throws IllegalArgumentException thrown if parameter doesn't pass test
     * @since 0.3.14
     */
    NamedKey createNamedKey(String keyString) throws IllegalArgumentException;

    /**
     * Delegation for ItemKey static method
     *
     * @param namespace the namespace
     * @param key       the key
     * @return the item key
     * @throws IllegalArgumentException thrown if entries don't pass tests
     * @since 0.3.14
     */
    ItemKey createItemKey(String namespace, String key) throws IllegalArgumentException;

    /**
     * Delegation for ItemKey static method
     *
     * @param keyString the keyString to turn into a ItemKey
     * @return the item key
     * @throws IllegalArgumentException thrown if a parameter doesn't pass tests
     * @since 0.3.23
     */
    ItemKey createItemKey(String keyString) throws IllegalArgumentException;

    /**
     * Delegation for ItemStack static method
     *
     * @param itemType the itemType to create with
     * @return the new stack
     * @throws IllegalArgumentException thrown if a parameter doesn't pass tests
     * @since 0.3.23
     */
    ItemStack createItemStack(ItemType itemType) throws IllegalArgumentException;

    /**
     * Delegation for BlockPos static method
     *
     * @param x x
     * @param y y
     * @param z z
     * @return the new BlockPos
     * @throws IllegalArgumentException thrown if a parameter doesn't pass tests
     * @since 0.3.26
     */
    BlockPos createBlockPos(int x, int y, int z) throws IllegalArgumentException;

    /**
     * Delegation for Position static method
     *
     * @param x x
     * @param y y
     * @param z z
     * @return the new Position
     * @throws IllegalArgumentException thrown if a parameter doesn't pass tests
     * @since 0.3.27
     */
    Position createPosition(float x, float y, float z) throws IllegalArgumentException;

    /**
     * Delegation for Vector static method
     *
     * @param x x
     * @param y y
     * @param z z
     * @return the new Vector
     * @throws IllegalArgumentException thrown if a parameter doesn't pass tests
     * @since 0.3.27
     */
    Vector createVector(float x, float y, float z) throws IllegalArgumentException;

    /**
     * Delegation for registry access
     *
     * @param clazz the class to get the registry of
     * @param <E>   type of registry
     * @return the registry
     * @throws IllegalArgumentException thrown if the parameter doesn't past tests
     * @since 0.3.14
     */
    <E extends KeyHolder<K>, K> Registry<E, K> getRegistry(Class<E> clazz) throws IllegalArgumentException;
}
