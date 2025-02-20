package sh.miles.voidcr.impl.world.block.entity;

import finalforeach.cosmicreach.blockentities.BlockEntityItemContainer;
import sh.miles.voidcr.world.block.entity.BlockEntityCrate;
import sh.miles.voidcr.world.inventory.container.ItemContainer;

public class VoidBlockEntityCrate extends VoidBlockEntity<BlockEntityItemContainer> implements BlockEntityCrate {
    public VoidBlockEntityCrate(final BlockEntityItemContainer mirror) {
        super(mirror);
    }

    @Override
    public ItemContainer getItemContainer() {
        return mirror.slotContainer.getVoidMirror();
    }
}
