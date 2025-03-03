package sh.miles.voidcr.testplugin;

import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.plugin.lifecycle.event.chat.pre.PrePlayerChatEvent;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.server.Server;

@NullMarked
public class TestStandardPlugin implements StandardPlugin {

    @Override
    public void initialize(final Server server) {
        server.getLifecycle().observe(this, PrePlayerChatEvent.class, (event, id) -> {
            event.setMessage("I am a loser");
        });
        server.getLogger().info("Hello, World! From Test Plugin!");
    }

    @Override
    public void disable(final Server server) {
        server.getLogger().info("Goodbye, World! From Test Plugin!");
    }
}
