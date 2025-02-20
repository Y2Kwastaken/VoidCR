package sh.miles.voidcr.world.block.entity;

import com.badlogic.gdx.graphics.Color;

/**
 * Block entity that represents a sign
 *
 * @since 0.3.26
 */
public interface BlockEntitySign extends BlockEntity {

    /**
     * Gets the sign text content
     *
     * @return the lines, which will always be of size 5, empty lines are denoted by an empty string
     * @since 0.3.26
     */
    String[] getLines();

    /**
     * Sets the lines of the sign
     *
     * @param lines the sign lines, which must be no more than 5
     * @throws IllegalArgumentException thrown if more than 5 lines are provided
     * @since 0.3.26
     */
    void setLines(String... lines) throws IllegalArgumentException;

    /**
     * Gets the color of the sign
     *
     * @return the sign color
     * @since 0.3.26
     */
    Color getColor();

    /**
     * Sets the color of the sign text
     *
     * @param color the color to use
     * @since 0.3.26
     */
    void setColor(Color color);

    /**
     * Gets the text size of this sign
     *
     * @return the text size
     * @since 0.3.26
     */
    float getTextSize();

    /**
     * Sets the text size of the sign
     *
     * @param size the new text size to set
     * @since 0.3.26
     */
    void setTextSize(float size);
}
