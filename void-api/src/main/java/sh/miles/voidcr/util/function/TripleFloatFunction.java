package sh.miles.voidcr.util.function;

/**
 * Wraps a function with 3 float inputs
 *
 * @param <R> the return type
 * @since 0.3.27
 */
@FunctionalInterface
public interface TripleFloatFunction<R> {
    /**
     * Does some computation on the provided float and returns a result
     *
     * @param f1 the first double
     * @param f2 the second double
     * @param f3 the third double
     * @return the computed result
     * @since 0.3.27
     */
    R compute(float f1, float f2, float f3);
}
