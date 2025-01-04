package sh.miles.voidcr;

import finalforeach.cosmicreach.server.ServerLauncher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sh.miles.voidcr.impl.server.VoidServer;

public final class Main {

    public static final Logger LOGGER = LogManager.getLogger("sh.miles.voidcr");

    public static void main(String[] args) {
        try {
            VoidServer.SERVER.getPluginLoader().loadPluginFiles(VoidServer.SERVER.getConfiguration().pluginsDirectory());
            ServerLauncher.main(args);
        } catch (Exception e) {
            LOGGER.error("Unable to start cosmic reach", e);
        }
    }

}
