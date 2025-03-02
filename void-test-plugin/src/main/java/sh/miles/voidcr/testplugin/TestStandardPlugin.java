package sh.miles.voidcr.testplugin;

import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.block.BlockState;
import sh.miles.voidcr.world.block.BlockType;

@NullMarked
public class TestStandardPlugin implements StandardPlugin {

    @Override
    public void initialize(final Server server) {
        server.getLifecycle().registerCommand(this, (builder) -> {
            builder.name("test")
                    .executor((executor, context) -> {
                        if (!(executor instanceof PlayerEntity player)) {
                            return;
                        }

                        final var gold = BlockType.GOLD_BLOCK.getDefaultBlockState();
                        for (final BlockState allBlockState : BlockType.LIGHT.getAllBlockStates()) {
                            System.out.println(allBlockState);
                        }
                        player.getPosition().coerce().subtract(0, 1, 0)
                                .bindTo(player.getWorld())
                                .setBlockState(BlockType.LIGHT.getBlockState("power", "on"))
                                .add(1, 0, 0).setBlockState(gold)
                                .add(-2, 0, 0).setBlockState(gold)
                                .add(0, 0, 1).setBlockState(gold)
                                .add(1, 0, 0).setBlockState(gold)
                                .add(1, 0, 0).setBlockState(gold)
                                .add(0, 0, -2).setBlockState(gold)
                                .add(-1, 0, 0).setBlockState(gold)
                                .add(-1, 0, 0).setBlockState(gold);
                    });
        });
        server.getLogger().info("Hello, World! From Test Plugin!");
    }

    @Override
    public void disable(final Server server) {
        server.getLogger().info("Goodbye, World! From Test Plugin!");
    }
}
