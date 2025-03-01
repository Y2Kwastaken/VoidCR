package sh.miles.voidcr.testplugin;

import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.server.Server;

@NullMarked
public class TestStandardPlugin implements StandardPlugin {

    @Override
    public void initialize(final Server server) {
        server.getLifecycle().registerCommand(this, (builder) -> {
            builder.name("command")
                    .description("A Command for testing")
                    .executor(((executor, context) -> {
                        if (executor == null) {
                            System.out.println("NOT PLAYER");
                        }

                        executor.sendMessage(context.getArgument(0));
                    }));
        });
        server.getLogger().info("Hello, World! From Test Plugin!");
    }

    @Override
    public void disable(final Server server) {
        server.getLogger().info("Goodbye, World! From Test Plugin!");
    }
}
