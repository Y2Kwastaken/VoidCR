package sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.pre;

import com.badlogic.gdx.graphics.Color;
import sh.miles.voidcr.plugin.lifecycle.Cancelable;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.PlayerSignUpdateEvent;
import sh.miles.voidcr.server.Server;

/**
 * Event that takes place before a sign is updated
 */
public interface PrePlayerSignUpdateEvent extends PlayerSignUpdateEvent, Cancelable, LifecycleEvent<Server> {

    /**
     * Sets the lines of the sign
     *
     * @param lines the sign lines, which must be no more than 5
     * @throws IllegalArgumentException thrown if more than 5 lines are provided
     * @since 0.3.26
     */
    void setLines(String... lines) throws IllegalArgumentException;

    /**
     * Sets the color of the sign text
     *
     * @param color the color to use
     * @since 0.3.26
     */
    void setColor(Color color);

    /**
     * Sets the text size of the sign
     *
     * @param size the new text size to set
     * @since 0.3.26
     */
    void setTextSize(float size);
}
