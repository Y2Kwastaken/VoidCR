package sh.miles.voidcr.world.inventory.item;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.world.inventory.container.Container;

/**
 * Represents a stack of {@link ItemType} that can be stored inside of a {@link Container}
 * <p>
 * ItemStack's on top of just storing multiples of items can store extra information that can be displayed
 *
 * @since 0.3.23
 */
public interface ItemStack {

    /**
     * Sets the provided ItemProperty on this item
     *
     * @param property the property to set
     * @param value    the value to set, which will override the previous property value
     * @param <V>      the property value type
     * @throws IllegalArgumentException if the property can not be set on the provided item
     * @since 0.3.23
     */
    <V> void set(ItemProperty<V> property, V value) throws IllegalArgumentException;

    /**
     * Reads the provided ItemProperty on this item
     *
     * @param property the property to get
     * @param <V>      the property value type
     * @return the value of the property, or null if the property isn't set
     * @since 0.3.23
     */
    @Nullable
    <V> V get(ItemProperty<V> property);

    /**
     * Determines whether or not the provided property is set, if false {@link #get(ItemProperty)} will return null
     *
     * @param property the property to check is set or not
     * @param <V>      the property value type
     * @return true if the property is set, otherwise false
     * @since 0.3.23
     */
    <V> boolean isSet(ItemProperty<V> property);

    /**
     * Determines whether or not this property can be set on this ItemStack, if false {@link #set(ItemProperty, Object)}
     * is guaranteed to throw and {@link #get(ItemProperty)} will return null
     *
     * @param property the property to check
     * @param <V>      the property value type
     * @return true if the property can be set on this ItemStack, otherwise false
     * @since 0.3.23
     */
    <V> boolean canSet(ItemProperty<V> property);

    /**
     * Gets the {@link ItemType} of this ItemStack
     *
     * @return the {@link ItemType}
     * @since 0.3.23
     */
    ItemType getItemType();
}
