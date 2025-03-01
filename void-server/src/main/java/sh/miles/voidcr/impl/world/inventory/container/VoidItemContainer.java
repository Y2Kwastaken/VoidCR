package sh.miles.voidcr.impl.world.inventory.container;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.entities.player.PlayerEntity;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.networking.packets.items.SlotSyncPacket;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import sh.miles.voidcr.impl.world.inventory.item.VoidItemStack;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.util.collection.SingleDynamicArray;
import sh.miles.voidcr.world.inventory.container.ItemContainer;
import sh.miles.voidcr.world.inventory.item.ItemStack;

public class VoidItemContainer implements ItemContainer, Mirrored<SlotContainer> {

    private final SlotContainer mirror;

    public VoidItemContainer(final SlotContainer mirror) {
        this.mirror = mirror;
    }

    @Override
    public void setItem(final int slot, final ItemStack item) {
        Preconditions.checkArgument(item != null, "The provided item must not be null");
        this.mirror.getSlot(slot).setItemStack(((VoidItemStack) item).getMirrorCopy(), true); // force slot updates
    }

    @Override
    public boolean addItem(final ItemStack item) {
        Preconditions.checkArgument(item != null, "The provided item must not be null");
        return this.mirror.addItemStack(((VoidItemStack) item).getMirrorCopy());
    }

    @Override
    public boolean hasItem(final int slot) {
        Preconditions.checkArgument(slot >= 0 && slot < mirror.getNumSlots(), "The provided slot must be in the range [0, %d)".formatted(mirror.getNumSlots()));
        return mirror.getSlot(slot).hasItemStack();
    }

    @Override
    public int getFirstEmpty() {
        return mirror.getFirstEmptyItemSlot().getSlotId();
    }

    @Override
    public boolean isEmpty() {
        return mirror.isEmpty();
    }

    @Override
    public int getSize() {
        return mirror.getNumSlots();
    }

    @Override
    public void clear() {
        this.mirror.clear();
    }

    @Override
    public SlotContainer getMirror() {
        return this.mirror;
    }

    @Override
    public void updateRemote() {
        final var owner = this.mirror.owner;
        if (owner instanceof PlayerEntity player && mirror.windowId != -1) {
            this.mirror.syncDirtySlots(this.mirror.windowId, ServerSingletons.getConnection(player.player));
        }
    }

    public void syncSlot(ItemSlot slot) {
        final var owner = this.mirror.owner;
        if (owner instanceof PlayerEntity player && mirror.windowId != -1) {
            ServerSingletons.getConnection(player.player).send(new SlotSyncPacket(mirror.windowId, new SingleDynamicArray<>(slot)));
        }
    }
}
