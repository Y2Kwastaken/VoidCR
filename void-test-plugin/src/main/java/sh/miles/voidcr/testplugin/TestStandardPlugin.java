package sh.miles.voidcr.testplugin;

import org.apache.logging.log4j.LogManager;
import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.server.registry.Registries;
import sh.miles.voidcr.server.registry.Registry;
import sh.miles.voidcr.world.block.BlockState;
import sh.miles.voidcr.world.block.BlockType;
import sh.miles.voidcr.world.inventory.item.ItemType;

@NullMarked
public class TestStandardPlugin implements StandardPlugin {

    @Override
    public void initialize(final Server server) {
        server.getLogger().info("Hello, World! From Test Plugin!");
    }

    @Override
    public void disable(final Server server) {
        server.getLogger().info("Goodbye, World! From Test Plugin!");
    }
}
