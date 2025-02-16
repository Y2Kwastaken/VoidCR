package sh.miles.voidcr.impl.world.inventory.item;

import finalforeach.cosmicreach.items.ItemStack;
import sh.miles.voidcr.util.NamedKey;

// init class for VoidItemProperty
public final class VoidItemProperties {

    public static final NamedKey MAX_STACK_SIZE = NamedKey.voidcr("max_stack_size");
    public static final NamedKey STACK_SIZE = NamedKey.voidcr("stack_size");
    public static final NamedKey DURABILITY = NamedKey.voidcr("durability");
    public static final NamedKey[] KEYS = {
            MAX_STACK_SIZE, STACK_SIZE, DURABILITY
    };

    public static <V> VoidItemProperty<V> create(NamedKey key) {
        if (MAX_STACK_SIZE.equals(key)) {
            return asType(new VoidItemProperty<>(key, (i, a) -> i.stackLimit = a, i -> i.stackLimit));
        }

        if (STACK_SIZE.equals(key)) {
            return asType(new VoidItemProperty<>(key, (i, s) -> i.amount = s, i -> i.amount));
        }

        if (DURABILITY.equals(key)) {
            return asType(new VoidItemProperty<>(key, (i, d) -> i.durability = d, i -> i.durability, ItemStack::hasDurability, ItemStack::hasDurability));
        }

        throw new IllegalArgumentException("Unknown ItemProperty named " + key);
    }

    private static <V> VoidItemProperty<V> asType(VoidItemProperty<?> property) {
        return (VoidItemProperty<V>) property;
    }
}
