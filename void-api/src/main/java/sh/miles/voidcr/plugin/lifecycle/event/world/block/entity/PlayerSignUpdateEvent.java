package sh.miles.voidcr.plugin.lifecycle.event.world.block.entity;

import com.badlogic.gdx.graphics.Color;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.plugin.lifecycle.event.world.WorldEvent;
import sh.miles.voidcr.world.position.BlockPos;

/**
 * An event triggered when a sign is done being edited on the client
 *
 * @since 0.3.26
 */
public interface PlayerSignUpdateEvent extends WorldEvent {

    /**
     * Gets the sign text content
     *
     * @return the lines, which will always be of size 5, empty lines are denoted by an empty string
     * @since 0.3.26
     */
    String[] getLines();


    /**
     * Gets the color of the sign
     *
     * @return the sign color
     * @since 0.3.26
     */
    Color getColor();

    /**
     * Gets the text size of this sign
     *
     * @return the text size
     * @since 0.3.26
     */
    float getTextSize();

    /**
     * Gets the block position of the sign that is being updated
     *
     * @return the block position
     * @since 0.3.26
     */
    BlockPos getBlockPos();

    /**
     * Gets the player who triggered the update
     *
     * @return the player
     * @since 0.3.26
     */
    PlayerEntity getPlayer();
}
