package sh.miles.voidcr.util.function;

/**
 * Wraps a function with 3 int inputs
 *
 * @param <R> the return type
 * @since 0.3.26
 */
@FunctionalInterface
public interface TripleIntFunction<R> {
    R compute(int i1, int i2, int i3);
}
