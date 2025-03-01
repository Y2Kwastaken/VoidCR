package sh.miles.voidcr.plugin.lifecycle.command;

import sh.miles.voidcr.util.annotations.SupportsExtending;

/**
 * Resolves a command from a given argument
 *
 * @param <R> the result from the parsed argument
 * @since 0.3.27
 */
@FunctionalInterface
@SupportsExtending
public interface CommandArgumentResolver<R> {
    /**
     * The resulting parsed argument
     *
     * @param literal the literal argument
     * @return the parse result
     * @throws IllegalStateException if the argument was unable to be parsed
     */
    R resolve(String literal) throws IllegalStateException;
}
