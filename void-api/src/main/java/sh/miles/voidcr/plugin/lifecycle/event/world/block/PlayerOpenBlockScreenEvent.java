package sh.miles.voidcr.plugin.lifecycle.event.world.block;

import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.world.block.entity.BlockEntity;

/**
 * Event that occurs when a player opens a Block screen
 *
 * @since 0.3.27
 */
public interface PlayerOpenBlockScreenEvent extends BlockEvent {

    /**
     * Gets hte player who opened the block screen
     *
     * @return the player
     * @since 0.3.27
     */
    PlayerEntity getPlayer();

    /**
     * Gets the opened BlockEntity
     *
     * @return the opened block entity
     * @since 0.3.27
     */
    BlockEntity getBlockEntity();
}
