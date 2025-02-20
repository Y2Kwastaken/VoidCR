package sh.miles.voidcr.plugin.lifecycle.event.world.block;


import sh.miles.voidcr.plugin.lifecycle.event.world.WorldEvent;
import sh.miles.voidcr.world.block.BlockState;

/**
 * Represents an event like structure shared between all BlockEvents
 * <p>
 * Note this class is not an actual event
 *
 * @since 0.3.26
 */
public interface BlockEvent extends WorldEvent {

    /**
     * Gets the state of the block
     *
     * @return the block state
     * @since 0.3.26
     */
    BlockState getState();
}
