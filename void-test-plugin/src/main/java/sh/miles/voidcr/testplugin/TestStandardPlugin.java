package sh.miles.voidcr.testplugin;

import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.server.registry.Registries;

@NullMarked
public class TestStandardPlugin implements StandardPlugin {

    @Override
    public void initialize(final Server server) {
        Registries.ITEM.forEach(item -> {
            System.out.println("Direct Item: " + item.getKey());
        });
        Registries.BLOCK.forEach((block) -> {
            block.getAllBlockStates().forEach(state -> {
                System.out.println(state.getItemType());
            });
        });
        server.getLogger().info("Hello, World! From Test Plugin!");
    }

    @Override
    public void disable(final Server server) {
        server.getLogger().info("Goodbye, World! From Test Plugin!");
    }
}
