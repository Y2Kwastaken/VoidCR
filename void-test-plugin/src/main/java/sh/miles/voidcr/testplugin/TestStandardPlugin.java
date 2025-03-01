package sh.miles.voidcr.testplugin;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.impl.entity.VoidPlayerEntity;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.server.Server;

@NullMarked
public class TestStandardPlugin implements StandardPlugin {

    enum Daylight {
        MIDNIGHT(30000),
        DUSK(20000),
        DAWN(0),
        MIDDAY(10000);

        public final int ticks;

        Daylight(int ticks) {
            this.ticks = ticks;
        }

        @Nullable
        public static Daylight fromString(String name) {
            for (final Daylight value : values()) {
                if (value.name().equalsIgnoreCase(name)) {
                    return value;
                }
            }

            return null;
        }
    }

    @Override
    public void initialize(final Server server) {
        server.getLifecycle().registerArgumentResolver(this, Daylight.class, (literal) -> {
            final Daylight light = Daylight.fromString(literal);
            if (light == null) {
                throw new IllegalStateException("Can not parse daylight from argument %s".formatted(light));
            }
            return light;
        });
        server.getLifecycle().registerCommand(this, (builder) -> {
            builder.name("time")
                    .yieldOnConflict(false)
                    .description("A Command that overwrites the default CosmicReach day command")
                    .executor(((executor, context) -> {
                        if (!(executor instanceof PlayerEntity player)) {
                            return;
                        }

                        final Daylight daylight = context.getArgumentOrElse(0, Daylight.class, Daylight.MIDDAY);
                        final var vplayer = (VoidPlayerEntity) player; // no API for this yet lol so this is internals
                        vplayer.getMirror().zone.getWorld().currentWorldTick = daylight.ticks;
                    }));
        });
        server.getLogger().info("Hello, World! From Test Plugin!");
    }

    @Override
    public void disable(final Server server) {
        server.getLogger().info("Goodbye, World! From Test Plugin!");
    }
}
