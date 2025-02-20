package sh.miles.voidcr.plugin.lifecycle.event.world.block;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.world.block.BlockState;
import sh.miles.voidcr.world.position.BlockPos;

/**
 * Event triggered when a player interacts with a block
 *
 * @since 0.3.27
 */
public interface PlayerInteractBlockEvent extends BlockEvent {

    /**
     * Gets the state that the interaction occurred at
     *
     * @return the state, or null if no block was targeted
     */
    @Override
    @Nullable
    BlockState getState();

    /**
     * Gets the location the interaction occurred at
     *
     * @return the block position, or null if no block was targeted
     * @since 0.3.27
     */
    @Nullable
    BlockPos getBlockPos();

    /**
     * Gets the player who interacted with the block
     *
     * @return the player
     * @since 0.3.27
     */
    PlayerEntity getPlayer();

}
