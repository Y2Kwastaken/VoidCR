package sh.miles.voidcr.impl.server;

import finalforeach.cosmicreach.chat.commands.Command;
import org.apache.logging.log4j.Logger;
import sh.miles.voidcr.Main;
import sh.miles.voidcr.feature.chat.command.VoidServerStopCommand;
import sh.miles.voidcr.impl.plugin.VoidPluginLoader;
import sh.miles.voidcr.impl.plugin.lifecycle.VoidLifecycleManager;
import sh.miles.voidcr.impl.server.configuration.VoidServerConfiguration;
import sh.miles.voidcr.impl.util.VoidMagicMethods;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.server.VoidCR;
import sh.miles.voidcr.util.MagicMethods;
import sh.miles.voidcr.util.VoidConstants;

import java.nio.file.Path;

public final class VoidServer implements Server {

    public static final VoidServer SERVER = new VoidServer();

    private final VoidServerConfiguration configuration;
    private final VoidPluginLoader pluginLoader;
    private final VoidMagicMethods magicMethods;
    private final VoidLifecycleManager<Server> lifecycle;
    private final Path serverFolder;
    private final Logger logger;

    private VoidServer() {
        this.logger = Main.LOGGER;
        this.serverFolder = VoidConstants.JAR_DIRECTORY;
        this.pluginLoader = new VoidPluginLoader(this);
        this.magicMethods = new VoidMagicMethods();
        this.lifecycle = new VoidLifecycleManager<>(this);
        this.configuration = VoidServerConfiguration.read(this);
    }

    @Override
    public VoidServerConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public VoidLifecycleManager<Server> getLifecycle() {
        return lifecycle;
    }

    @Override
    public Path getServerFolder() {
        return this.serverFolder;
    }

    @Override
    public MagicMethods getMagic() {
        return this.magicMethods;
    }

    public VoidPluginLoader getPluginLoader() {
        return pluginLoader;
    }

    public VoidServer load() {
        registerInternalCommands();

        VoidCR.setServer(this);
        pluginLoader.enablePlugins();
        return this;
    }

    public void unload() {
        pluginLoader.disablePlugins();
    }

    private void registerInternalCommands() {
        Command.registerCommand(VoidServerStopCommand::new, "stop", "shutdown");
    }
}
