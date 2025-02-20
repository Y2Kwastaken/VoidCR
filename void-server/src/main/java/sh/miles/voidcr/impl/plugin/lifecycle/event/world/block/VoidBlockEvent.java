package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block;

import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.VoidWorldEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.BlockEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.block.BlockState;

public abstract class VoidBlockEvent extends VoidWorldEvent implements BlockEvent {

    protected BlockState state;

    public VoidBlockEvent(final Server context, final Zone zone, final finalforeach.cosmicreach.blocks.BlockState state) {
        super(context, zone);
        this.state = state == null ? null : state.getVoidMirror();
    }

    @Override
    public BlockState getState() {
        return this.state;
    }
}
