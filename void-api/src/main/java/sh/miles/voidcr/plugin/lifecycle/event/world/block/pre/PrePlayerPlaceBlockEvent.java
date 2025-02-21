package sh.miles.voidcr.plugin.lifecycle.event.world.block.pre;

import sh.miles.voidcr.plugin.lifecycle.Cancelable;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.PlayerPlaceBlockEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.block.BlockState;

/**
 * Event that occurs prior to a player placing a block
 *
 * @since 0.3.26
 */
public interface PrePlayerPlaceBlockEvent extends PlayerPlaceBlockEvent, Cancelable, LifecycleEvent<Server> {
    /**
     * Sets the block state that is provided after the place event occurs
     *
     * @param state the state actually placed
     * @since 0.3.26
     */
    void setBlockState(BlockState state);
}
