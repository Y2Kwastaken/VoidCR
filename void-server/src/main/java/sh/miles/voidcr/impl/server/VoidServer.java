package sh.miles.voidcr.impl.server;

import finalforeach.cosmicreach.chat.commands.Command;
import sh.miles.voidcr.feature.chat.command.VoidServerStopCommand;
import sh.miles.voidcr.impl.plugin.PluginLoadGraph;
import sh.miles.voidcr.impl.plugin.VoidPluginLoader;
import sh.miles.voidcr.impl.plugin.meta.VoidPluginMeta;
import sh.miles.voidcr.plugin.exception.PluginLoadException;
import sh.miles.voidcr.plugin.meta.PluginMeta;
import sh.miles.voidcr.server.Server;

import java.util.List;

public final class VoidServer implements Server {

    public VoidServer() {

    }

    public VoidServer load() {
        registerCommands();
        loadPlugins();
        return this;
    }

    private void registerCommands() {
        Command.registerCommand(VoidServerStopCommand::new, "stop", "shutdown");
    }

    private void loadPlugins() {
        final VoidPluginLoader loader = VoidPluginLoader.INSTANCE;
        final List<PluginMeta> loadOrder;
        final PluginLoadGraph graph = new PluginLoadGraph(loader.getMetas().size());
        try {
            loadOrder = graph.generate(loader.getMetas());
        } catch (PluginLoadException e) {
            throw new IllegalStateException(e);
        }

        for (final PluginMeta meta : loadOrder) {
            loader.loadClassLoader((VoidPluginMeta) meta);
        }
    }
}
