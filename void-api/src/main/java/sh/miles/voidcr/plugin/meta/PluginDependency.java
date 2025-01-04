package sh.miles.voidcr.plugin.meta;

/**
 * Defines a dependency that a plugin can have.
 *
 * @since 0.3.14
 */
public interface PluginDependency {

    /**
     * Gets the name of the dependency.
     *
     * @return the name
     * @since 0.3.14
     */
    String name();

    /**
     * Checks whether or not this dependency is required.
     *
     * @return true if required, otherwise false
     * @since 0.3.14
     */
    boolean required();

    /**
     * Checks whether or not this dependency should be loaded before this plugin.
     *
     * @return true if it should be loaded before, otherwise false
     * @since 0.3.14
     */
    boolean loadBefore();

    /**
     * Checks whether or not this plugin should have its classpath be merged into this plugin.
     *
     * @return true if the classpath should be merged, otherwise false
     * @since 0.3.14
     */
    boolean mergeClasspath();
}
