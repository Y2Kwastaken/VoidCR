package sh.miles.voidcr.plugin.lifecycle.event.world.block;

import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.world.position.BlockPos;

/**
 * Event triggered when a player breaks a block
 *
 * @since 0.3.26
 */
public interface PlayerBreakBlockEvent extends BlockEvent {

    /**
     * Gets the location the block was placed at
     *
     * @return the block position
     * @since 0.3.26
     */
    BlockPos getBlockPos();

    /**
     * Gets the player who placed the block
     *
     * @return the player
     * @since 0.3.26
     */
    PlayerEntity getPlayer();
}
