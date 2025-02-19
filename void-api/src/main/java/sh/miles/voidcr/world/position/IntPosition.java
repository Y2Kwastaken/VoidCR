package sh.miles.voidcr.world.position;

import sh.miles.voidcr.util.function.TripleIntFunction;

/**
 * Represents some position on some 3d plane that is a whole integer
 *
 * @param <T> the type inheriting this IntPosition
 * @since 0.3.26
 */
public interface IntPosition<T extends IntPosition<T>> {

    /**
     * The x value
     *
     * @return int
     * @since 0.3.26
     */
    int x();

    /**
     * The y value
     *
     * @return int
     * @since 0.3.26
     */
    int y();

    /**
     * The z value
     *
     * @return int
     * @since 0.3.26
     */
    int z();

    /**
     * Makes changes to the IntPosition raw values through an implementation of {@link TripleIntFunction}. These changes
     * are applied to the returned IntPosition
     * <p>
     * This method should be used over {@link #add(int, int, int)}, {@link #subtract(int, int, int)} or any other
     * provided operator for compound operations. This method will have less of an impact due to only cloning once.
     *
     * @param changes the changes to apply
     * @return the new IntPosition
     * @since 0.3.26
     */
    T withChanges(TripleIntFunction<T> changes);

    /**
     * Adds values to this IntPosition
     *
     * @param x the x value to add
     * @param y the y value to add
     * @param z the z value to add
     * @return the new IntPosition
     * @since 0.3.26
     */
    T add(int x, int y, int z);

    /**
     * Subtracts values from this IntPosition
     *
     * @param x the x value to subtract
     * @param y the y value to subtract
     * @param z the z value to subtract
     * @return the new IntPosition
     * @since 0.3.26
     */
    default T subtract(int x, int y, int z) {
        return add(-x, -y, -z);
    }

    /**
     * Multiplies values against this IntPosition
     *
     * @param x the x value to multiply by
     * @param y the y value to multiply by
     * @param z the z value to multiply by
     * @return the new IntPosition
     * @since 0.3.26
     */
    T multiply(int x, int y, int z);

    /**
     * Divides values against this IntPosition
     *
     * @param x the x to divide by
     * @param y the y to divide by
     * @param z the z to divide by
     * @return the new IntPosition
     * @since 0.3.26
     */
    T divide(int x, int y, int z);
}
