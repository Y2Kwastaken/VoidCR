package sh.miles.voidcr.world.inventory.item;

import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.NamedKey;

/**
 * Represents a property on an item
 *
 * @param <V> the value type of the property
 * @since 0.3.23
 */
public interface ItemProperty<V> extends Keyed {

    /**
     * A Property for dictating the max stack size of an item
     *
     * @since 0.3.23
     */
    ItemProperty<Integer> MAX_STACK_SIZE = property("max_stack_size");
    /**
     * A Property for the current stack size on an item
     *
     * @since 0.3.23
     */
    ItemProperty<Integer> STACK_SIZE = property("stack_size");
    /**
     * A Property for the durability on an item
     *
     * @since 0.3.23
     */
    ItemProperty<Integer> DURABILITY = property("durability");

    private static <V> ItemProperty<V> property(String key) {
        return (ItemProperty<V>) Registries.ITEM_PROPERTY.getOrThrow(NamedKey.voidcr(key));
    }

}
