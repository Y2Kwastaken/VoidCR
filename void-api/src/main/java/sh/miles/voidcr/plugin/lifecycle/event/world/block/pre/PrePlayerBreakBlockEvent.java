package sh.miles.voidcr.plugin.lifecycle.event.world.block.pre;

import sh.miles.voidcr.plugin.lifecycle.Cancelable;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.PlayerBreakBlockEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.block.BlockState;

public interface PrePlayerBreakBlockEvent extends PlayerBreakBlockEvent, Cancelable, LifecycleEvent<Server> {
    /**
     * Sets the block state that is provided after the break event occurs
     * <p>
     * using this method will change the eventual drop
     *
     * @param state the state actually placed
     * @since 0.3.26
     */
    void setBlockState(BlockState state);
}
