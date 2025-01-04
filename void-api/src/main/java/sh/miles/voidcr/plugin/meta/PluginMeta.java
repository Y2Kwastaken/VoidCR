package sh.miles.voidcr.plugin.meta;

import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.plugin.type.StandardPlugin;

import java.util.List;
import java.util.Set;

/**
 * Defines the meta of a plugin.
 *
 * @since 0.3.14
 */
public interface PluginMeta {

    /**
     * The name of the plugin.
     *
     * @return the plugin name
     * @since 0.3.14
     */
    String name();

    /**
     * The plugins main class in string form
     *
     * @return the main class
     * @since 0.3.14
     */
    String main();

    /**
     * Gets the main class of this plugin. This method can return null if the plugin's main class has not yet been
     * loaded
     *
     * @return the plugin main class
     * @since 0.3.14
     */
    @Nullable
    Class<? extends StandardPlugin> mainClass();

    /**
     * The version of the plugin.
     *
     * @return the plugin version
     * @since 0.3.14
     */
    String version();

    /**
     * The name of the author of this plugin.
     *
     * @return the plugin author's name
     * @since 0.3.14
     */
    String author();

    /**
     * Gets a list of contributors of this plugin.
     *
     * @return the list of contributors
     * @since 0.3.14
     */
    List<String> contributors();

    /**
     * Gets the API version that this plugin is implementing. Generally only
     * used in case of the need for temporary compatibility layers.
     *
     * @return the api version the plugin uses
     * @since 0.3.14
     */
    PluginApiVersion apiVersion();

    /**
     * @return a set of dependencies
     * @since 0.3.14
     * <p>
     * PluginApiVersion apiVersion();
     * <p>
     * /** Gets the set of dependencies of the plugin.
     * @since 0.3.14
     */
    Set<PluginDependency> dependencies();

    /**
     * Attempts to get a dependency of this plugin from the given name.
     *
     * @param name the name of the plugin.
     * @return the the dependency or null
     * @since 0.3.14
     */
    @Nullable
    PluginDependency getDependency(final String name);
}
