package sh.miles.voidcr.testplugin;

import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.server.registry.Registries;

@NullMarked
public class TestStandardPlugin implements StandardPlugin {

    @Override
    public void initialize(final Server server) {
        Registries.ITEM_PROPERTY.forEach((i) -> System.out.println(i.key()));
        server.getLogger().info("Hello, World! From Test Plugin!");
    }

    @Override
    public void disable(final Server server) {
        server.getLogger().info("Goodbye, World! From Test Plugin!");
    }
}
