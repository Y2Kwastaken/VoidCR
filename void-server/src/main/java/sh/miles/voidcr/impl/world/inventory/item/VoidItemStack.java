package sh.miles.voidcr.impl.world.inventory.item;

import com.google.common.base.Preconditions;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.world.inventory.item.ItemProperty;
import sh.miles.voidcr.world.inventory.item.ItemStack;
import sh.miles.voidcr.world.inventory.item.ItemType;

public final class VoidItemStack implements ItemStack, Mirrored<finalforeach.cosmicreach.items.ItemStack> {

    private final finalforeach.cosmicreach.items.ItemStack mirror;

    public VoidItemStack(finalforeach.cosmicreach.items.ItemStack mirror) {
        this.mirror = mirror;
    }

    @Override
    public <V> void set(final ItemProperty<V> property, final V value) {
        Preconditions.checkArgument(property != null, "The provided property must not be null");
        Preconditions.checkArgument(value != null, "The provided value must not be null");
        final var prop = (VoidItemProperty<V>) property;
        if (prop.canApply().test(this.mirror)) {
            prop.apply().accept(this.mirror, value);
        }

        throw new IllegalArgumentException("The provided property can not be provided to the item " + getItemType());
    }

    @Override
    public <V> @Nullable V get(final ItemProperty<V> property) {
        Preconditions.checkArgument(property != null, "The provided property must not be null");
        final var prop = (VoidItemProperty<V>) property;
        if (prop.isCurrentlySet().test(this.mirror)) {
            return prop.get().apply(this.mirror);
        }
        return null;
    }

    @Override
    public <V> boolean isSet(final ItemProperty<V> property) {
        Preconditions.checkArgument(property != null, "The provided property must not be null");
        return ((VoidItemProperty<V>) property).isCurrentlySet().test(this.mirror);
    }

    @Override
    public <V> boolean canSet(final ItemProperty<V> property) {
        Preconditions.checkArgument(property != null, "The provided property must not be null");
        return ((VoidItemProperty<V>) property).canApply().test(this.mirror);
    }

    @Override
    public ItemType getItemType() {
        return VoidItemType.toVoid(mirror.getItem());
    }

    @Override
    public finalforeach.cosmicreach.items.ItemStack getMirror() {
        return this.mirror;
    }
}
