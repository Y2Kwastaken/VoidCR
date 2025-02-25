package sh.miles.voidcr.world.position;

import sh.miles.voidcr.util.function.TripleDoubleFunction;

/**
 * Represents some position on some 3d plane
 *
 * @param <T> the type inheriting this DecimalPosition
 * @since 0.3.27
 */
public interface DecimalPosition<T extends DecimalPosition<T>> {

    /**
     * The x position
     *
     * @return double
     * @since 0.3.27
     */
    double x();

    /**
     * The y position
     *
     * @return double
     * @since 0.3.27
     */
    double y();

    /**
     * The z position
     *
     * @return double
     * @since 0.3.27
     */
    double z();

    /**
     * Makes changes to the DecimalPosition raw values through an implementation of {@link TripleDoubleFunction}. These
     * changes are applied to the returned DecimalPosition
     * <p>
     * This method should be used over {@link #add(double, double, double)}, {@link #subtract(double, double, double)} or any other
     * provided operator for compound operations. This method will have less of an impact due to only cloning once.
     *
     * @param changes the changes to apply
     * @return the new DecimalPosition
     * @since 0.3.27
     */
    T withChanges(TripleDoubleFunction<T> changes);

    /**
     * Adds values to this DecimalPosition
     *
     * @param x the x value to add
     * @param y the y value to add
     * @param z the z value to add
     * @return the new DecimalPosition
     * @since 0.3.27
     */
    T add(double x, double y, double z);

    /**
     * Subtracts values from this DecimalPosition
     *
     * @param x the x value to subtract
     * @param y the y value to subtract
     * @param z the z value to subtract
     * @return the new DecimalPosition
     * @since 0.3.27
     */
    default T subtract(double x, double y, double z) {
        return add(-x, -y, -z);
    }

    /**
     * Multiplies values against this DecimalPosition
     *
     * @param x the x value to multiply by
     * @param y the y value to multiply by
     * @param z the z value to multiply by
     * @return the new DecimalPosition
     * @since 0.3.27
     */
    T multiply(double x, double y, double z);

    /**
     * Divides values against this DecimalPosition
     *
     * @param x the x to divide by
     * @param y the y to divide by
     * @param z the z to divide by
     * @return the new DecimalPosition
     * @since 0.3.27
     */
    T divide(double x, double y, double z);
}
