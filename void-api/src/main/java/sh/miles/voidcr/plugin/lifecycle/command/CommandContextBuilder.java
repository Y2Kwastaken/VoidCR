package sh.miles.voidcr.plugin.lifecycle.command;

import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.plugin.meta.PluginMeta;

/**
 * A Builder used during the command process to create a larger context to how a command will act
 * <p>
 * This builder has 2 required options namely {@link #name(String)} and {@link #executor(CommandExecutor)}. It is also
 * recommended to set your intent through {@link #yieldOnConflict(boolean)}
 *
 * @param <C> the initialization context for the {@link LifecycleAware} object
 * @since 0.3.27
 */
public interface CommandContextBuilder<C> {

    /**
     * Prefers a specific source for the given class types
     *
     * @param owner the preferred source
     * @param type  the types to prefer from this source
     * @return this
     * @since 0.3.27
     */
    CommandContextBuilder<C> preferSourceForTypes(LifecycleAware<C> owner, Class<?>... type);

    /**
     * Prefers argument sources from a specific owner, if set {@link CommandArgumentResolver}s from this owner will be
     * preferred over your own resolvers.
     * <p>
     * An example of preferential treatment is as follows. If you set preference from Plugin B by providing their
     * LifecycleAware object through this class and you both register MyObject.class providers, the MyObject provider
     * from Plugin B will be used instead of your own.
     *
     * @param owner the owner to prefer resolvers from
     * @return this
     * @since 0.3.27
     */
    CommandContextBuilder<C> preferArgumentSource(LifecycleAware<C> owner);

    /**
     * Sets the name of this command
     *
     * @param name the command name
     * @return this
     * @since 0.3.27
     */
    CommandContextBuilder<C> name(String name);

    /**
     * Description displayed in /help
     *
     * @param description the description of this command
     * @return this
     * @since 0.3.27
     */
    CommandContextBuilder<C> description(String description);

    /**
     * Decided whether or not to yield this commands name on a conflict
     * <p>
     * Note yielding does not mean this command is not accessible it can be accessed through the combination of
     * {@link PluginMeta#name()}:{@link #name(String)}. Your plugin's name is set through the plugin.json
     * <p>
     * Not yielding commands means when a conflict occurs this plugin will attempt to keep its name defined in
     * {@link #name(String)} for as long as possible if it is not possible to settle on a name this command will simply
     * not be added and a warning log will be sent to console
     * <p>
     * By default your plugin automatically yields its name on conflict
     *
     * @param doYield whether or not to yield on a naming conflict
     * @return this
     * @since 0.3.27
     */
    CommandContextBuilder<C> yieldOnConflict(boolean doYield);

    /**
     * Provides some execution function to this context builder
     *
     * @param executor the code run while the execution takes place
     * @return this
     * @since 0.3.27
     */
    CommandContextBuilder<C> executor(CommandExecutor executor);
}
