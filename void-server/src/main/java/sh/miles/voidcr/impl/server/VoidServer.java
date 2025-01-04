package sh.miles.voidcr.impl.server;

import finalforeach.cosmicreach.chat.commands.Command;
import org.apache.logging.log4j.Logger;
import sh.miles.voidcr.Main;
import sh.miles.voidcr.feature.chat.command.VoidServerStopCommand;
import sh.miles.voidcr.impl.plugin.VoidPluginLoader;
import sh.miles.voidcr.impl.server.configuration.VoidServerConfiguration;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.util.VoidConstants;

import java.nio.file.Path;

public final class VoidServer implements Server {

    public static final VoidServer SERVER = new VoidServer();

    private final VoidServerConfiguration configuration;
    private final VoidPluginLoader pluginLoader;
    private final Path serverFolder;
    private final Logger logger;

    private VoidServer() {
        this.logger = Main.LOGGER;
        this.serverFolder = VoidConstants.JAR_DIRECTORY;
        this.pluginLoader = new VoidPluginLoader(this);
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
    public Path getServerFolder() {
        return this.serverFolder;
    }

    public VoidPluginLoader getPluginLoader() {
        return pluginLoader;
    }

    public VoidServer load() {
        registerCommands();
        pluginLoader.enablePlugins();
        return this;
    }

    public void unload() {
        pluginLoader.disablePlugins();
    }

    private void registerCommands() {
        Command.registerCommand(VoidServerStopCommand::new, "stop", "shutdown");
    }
}
