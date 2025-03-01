package sh.miles.voidcr.plugin.lifecycle.command;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.plugin.lifecycle.LifecycleManager;

/**
 * Provides useful contextual methods for commands
 *
 * @since 0.3.27
 */
public interface CommandContext {

    /**
     * Attempts to get an argument of the provided type or returns the provided fallback see
     * {@link #getArgument(int, int, Class)} for more information
     *
     * @param index    the index of the argument
     * @param type     the type of the argument
     * @param fallback the fallback if the argument fails to parse
     * @param <R>      the argument type generalized
     * @return the argument or fallback
     * @throws IndexOutOfBoundsException thrown if the index is out of bounds
     * @throws IllegalArgumentException  thrown if there exists no resolver for the requested type
     * @since 0.3.27
     */
    <R> R getArgumentOrElse(int index, Class<R> type, @Nullable R fallback) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * Attempts to get an argument of the provided type see {@link #getArgument(int, int, Class)} for more information
     *
     * @param index the index of the argument
     * @param type  the type of argument
     * @param <R>   the argument type generalized
     * @return the argument
     * @throws IndexOutOfBoundsException thrown if the index is out of bounds
     * @throws IllegalArgumentException  thrown if there exists no resolver for the requested type
     * @throws IllegalStateException     thrown if there exists a resolver but it failed to parse the index
     * @since 0.3.27
     */
    <R> R getArgument(int index, Class<R> type) throws IndexOutOfBoundsException, IllegalArgumentException, IllegalStateException;

    /**
     * Attempts to get an argument of the provided type.
     * <p>
     * VoidCR provides some default types that can be parsed those are listed here, these are attempted last in the
     * chain of attempting to retrieve resolvable arguments so they can be overridden easily
     * <li>Double</li>
     * <li>Float</li>
     * <li>Integer</li>
     * <li>Long</li>
     * <li>PlayerEntity</li>
     * <li>String</li>
     * <p>
     * Not all types are allowed to be parsed by default. You as the API consumer can register these argument resolvers
     * by registering them with the valid lifecycle manager see
     * {@link LifecycleManager#registerArgumentResolver(LifecycleAware, Class, CommandArgumentResolver)} by default your
     * plugin's resolvers are automatically the only choice, however, other preferred resolvers can be provided by using
     * the {@link CommandContextBuilder#preferArgumentSource(LifecycleAware)} and
     * {@link CommandContextBuilder#preferSourceForTypes(LifecycleAware, Class[])} to provide much more fine tuned
     * control over this behavior
     *
     * @param startIndex the inclusive starting index for the argument
     * @param endIndex   the inclusive ending index for the argument
     * @param type       the type of argument being parsed
     * @param <R>        the argument type generalized
     * @return the argument
     * @throws IndexOutOfBoundsException thrown if the startingIndex or endingIndex is out of bounds
     * @throws IllegalArgumentException  thrown if there exists no resolver for the requested type
     * @throws IllegalStateException     thrown if there exists ar resolver but it failed to parse the index
     * @since 0.3.27
     */
    <R> R getArgument(int startIndex, int endIndex, Class<R> type) throws IndexOutOfBoundsException, IllegalArgumentException, IllegalStateException;

    /**
     * Gets the argument at the given index
     *
     * @param index the index to get the argument at
     * @return the argument
     * @throws IndexOutOfBoundsException thrown if the index is out of bounds
     * @since 0.3.27
     */
    String getArgument(int index) throws IndexOutOfBoundsException;

    /**
     * Gets the amount of arguments provided
     *
     * @return a number representing the amount of arguments
     * @since 0.3.27
     */
    int getArgumentCount();
}
