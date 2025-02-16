package sh.miles.voidcr.impl.world.inventory.item;

import finalforeach.cosmicreach.items.ItemStack;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.inventory.item.ItemProperty;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public record VoidItemProperty<V>(NamedKey key, BiConsumer<ItemStack, V> apply, Function<ItemStack, V> get,
                                  Predicate<ItemStack> canApply,
                                  Predicate<ItemStack> isCurrentlySet) implements ItemProperty<V> {

    public static Predicate<ItemStack> ALWAYS_TRUE = (ItemStack item) -> true;

    public VoidItemProperty(NamedKey key, BiConsumer<ItemStack, V> apply, Function<ItemStack, V> get) {
        this(key, apply, get, ALWAYS_TRUE, ALWAYS_TRUE);
    }
}
