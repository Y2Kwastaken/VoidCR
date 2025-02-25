package sh.miles.voidcr.util.function;

/**
 * Wraps a function with 3 double inputs
 *
 * @param <R> the return type
 * @since 0.3.27
 */
@FunctionalInterface
public interface TripleDoubleFunction<R> {
    /**
     * Does some computation on the provided doubles and returns a result
     *
     * @param d1 the first double
     * @param d2 the second double
     * @param d3 the third double
     * @return the computed result
     * @since 0.3.27
     */
    R compute(double d1, double d2, double d3);
}
