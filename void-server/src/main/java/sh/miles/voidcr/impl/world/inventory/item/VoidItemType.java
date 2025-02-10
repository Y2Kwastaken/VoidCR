package sh.miles.voidcr.impl.world.inventory.item;

import finalforeach.cosmicreach.items.Item;
import sh.miles.voidcr.impl.util.VoidNamedKey;
import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.inventory.item.ItemType;

import java.util.Objects;

public class VoidItemType implements ItemType, Mirrored<Item> {

    public static Item toCosmicReach(ItemType item) {
        return Item.allItems.get(((VoidNamedKey) item.getKey()).getCosmicReachId());
    }

    public static VoidItemType toVoid(Item item) {
        return (VoidItemType) Registries.ITEM.get(NamedKey.key(item.getID()));
    }

    private final Item mirror;
    private final NamedKey key;

    public VoidItemType(Item mirror) {
        this.mirror = mirror;
        this.key = NamedKey.key(mirror.getID());
    }

    @Override
    public Item getMirror() {
        return this.mirror;
    }

    @Override
    public int getMaxStackSize() {
        return this.mirror.getDefaultStackLimit();
    }

    @Override
    public boolean isInCatalog() {
        return this.mirror.isCatalogHidden();
    }

    @Override
    public NamedKey getKey() {
        return this.key;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final VoidItemType that)) return false;
        return Objects.equals(mirror, that.mirror) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mirror, key);
    }

    @Override
    public String toString() {
        return this.key.toString();
    }
}
