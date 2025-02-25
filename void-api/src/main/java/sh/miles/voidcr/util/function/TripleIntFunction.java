package sh.miles.voidcr.util.function;

/**
 * Wraps a function with 3 int inputs
 *
 * @param <R> the return type
 * @since 0.3.26
 */
@FunctionalInterface
public interface TripleIntFunction<R> {
    /**
     * Does some computation on the provided integers and returns a result
     *
     * @param i1 the first integer
     * @param i2 the second integer
     * @param i3 the third integer
     * @return the computed result
     * @since 0.3.26
     */
    R compute(int i1, int i2, int i3);
}
