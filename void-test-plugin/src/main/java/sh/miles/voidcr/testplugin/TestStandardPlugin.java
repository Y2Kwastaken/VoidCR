package sh.miles.voidcr.testplugin;

import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.plugin.lifecycle.event.entity.pre.PreEntityDamageEvent;
import sh.miles.voidcr.plugin.type.StandardPlugin;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.inventory.item.ItemStack;
import sh.miles.voidcr.world.inventory.item.ItemType;
import sh.miles.voidcr.world.position.BlockPos;

@NullMarked
public class TestStandardPlugin implements StandardPlugin {

    @Override
    public void initialize(final Server server) {
        final byte[] in = BlockPos.create(1, 2, 3).toBytes();
        final BlockPos out = BlockPos.fromBytes(in);
        System.out.println(out);
        server.getLifecycle().observe(this, PreEntityDamageEvent.class, (PreEntityDamageEvent event, Integer id) -> {
            if (event.getDamager() instanceof PlayerEntity player) {
                player.getInventory().addItem(ItemStack.create(ItemType.LATEX));
            }
        });
        server.getLogger().info("Hello, World! From Test Plugin!");
    }

    @Override
    public void disable(final Server server) {
        server.getLogger().info("Goodbye, World! From Test Plugin!");
    }
}
