package sh.miles.voidcr.impl.world.block.entity;

import sh.miles.voidcr.world.block.entity.BlockEntityFurnace;
import sh.miles.voidcr.world.inventory.container.ItemContainer;

public class VoidBlockEntityFurnace extends VoidBlockEntity<finalforeach.cosmicreach.blockentities.BlockEntityFurnace> implements BlockEntityFurnace {
    public VoidBlockEntityFurnace(final finalforeach.cosmicreach.blockentities.BlockEntityFurnace mirror) {
        super(mirror);
    }

    @Override
    public int getStartingFuelTicks() {
        return mirror.progressTicks;
    }

    @Override
    public int getFuelTicks() {
        return mirror.fuelTicks;
    }

    @Override
    public int getProgressTicks() {
        return mirror.progressTicks;
    }

    @Override
    public int getMaxProgressTicks() {
        return finalforeach.cosmicreach.blockentities.BlockEntityFurnace.MAX_PROGRESS_TICKS;
    }

    @Override
    public ItemContainer getItemContainer() {
        return mirror.slotContainer.getVoidMirror();
    }
}
