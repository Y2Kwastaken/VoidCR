package sh.miles.voidcr.impl.plugin.lifecycle.event;

import com.badlogic.gdx.graphics.Color;
import finalforeach.cosmicreach.BlockEntityScreenInfo;
import finalforeach.cosmicreach.blockentities.BlockEntitySign;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import sh.miles.voidcr.impl.plugin.lifecycle.event.entity.post.VoidPostEntityDamageEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.entity.pre.VoidPreEntityDamageEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.post.VoidPostPlayerSignUpdateEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.pre.VoidPrePlayerSignUpdateEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.post.VoidPostPlayerBreakBlockEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.post.VoidPostPlayerInteractEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.post.VoidPostPlayerOpenBlockScreenEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.post.VoidPostPlayerPlaceBlockEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.pre.VoidPrePlayerBreakBlockEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.pre.VoidPrePlayerInteractBlockEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.pre.VoidPrePlayerOpenBlockScreenEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.pre.VoidPrePlayerPlaceBlockEvent;

import static sh.miles.voidcr.impl.plugin.lifecycle.VoidLifecycleManager.dispatchEvent;

public final class VoidEventFactory {

    private VoidEventFactory() {
        throw new UnsupportedOperationException("Can not create instance of utility class VoidEventFactory");
    }

    public static VoidPreEntityDamageEvent preEntityDamage(float damage, int invulnerabilityFrames, finalforeach.cosmicreach.entities.Entity damager, finalforeach.cosmicreach.entities.Entity effected) {
        return dispatchEvent(ctx -> new VoidPreEntityDamageEvent(ctx, effected, effected.zone, invulnerabilityFrames, damage, damager));
    }

    public static void postEntityDamage(float damage, int invulnerabilityFrames, finalforeach.cosmicreach.entities.Entity damager, finalforeach.cosmicreach.entities.Entity effected) {
        dispatchEvent(ctx -> new VoidPostEntityDamageEvent(ctx, effected, effected.zone, invulnerabilityFrames, damage, damager));
    }

    public static VoidPrePlayerSignUpdateEvent prePlayerSignUpdate(NetworkIdentity actor, BlockEntitySign sign, String[] lines, float fontSize, int color) {
        return dispatchEvent(ctx -> new VoidPrePlayerSignUpdateEvent(ctx, sign, lines, new Color(color), fontSize, actor.getPlayer()));
    }

    public static void postPlayerSignUpdate(NetworkIdentity actor, BlockEntitySign sign) {
        dispatchEvent(ctx -> new VoidPostPlayerSignUpdateEvent(ctx, sign, actor.getPlayer()));
    }

    public static VoidPrePlayerPlaceBlockEvent prePlayerPlaceBlock(NetworkIdentity identity, BlockPosition position, BlockState target) {
        return dispatchEvent(ctx -> new VoidPrePlayerPlaceBlockEvent(ctx, identity.getPlayer(), position, target));
    }

    public static void postPlayerPlaceBlock(NetworkIdentity identity, BlockPosition position, BlockState target) {
        dispatchEvent(ctx -> new VoidPostPlayerPlaceBlockEvent(ctx, identity.getPlayer(), position, target));
    }

    public static VoidPrePlayerBreakBlockEvent prePlayerBreakBlock(NetworkIdentity identity, BlockPosition position, BlockState target) {
        return dispatchEvent(ctx -> new VoidPrePlayerBreakBlockEvent(ctx, identity.getPlayer(), position, target));
    }

    public static void postPlayerBreakBlock(NetworkIdentity identity, BlockPosition position, BlockState state) {
        dispatchEvent(ctx -> new VoidPostPlayerBreakBlockEvent(ctx, identity.getPlayer(), position, state));
    }

    public static VoidPrePlayerInteractBlockEvent prePlayerInteractBlock(NetworkIdentity identity, BlockPosition position, BlockState target) {
        return dispatchEvent(ctx -> new VoidPrePlayerInteractBlockEvent(ctx, identity.getPlayer(), position, target));
    }

    public static void postPlayerInteractBlock(NetworkIdentity identity, BlockPosition position, BlockState target) {
        dispatchEvent(ctx -> new VoidPostPlayerInteractEvent(ctx, identity.getPlayer(), position, target));
    }

    public static boolean prePlayerOpenBlockScreen(BlockEntityScreenInfo info) {
        if (info.player().getZone() != info.blockEntity().getZone()) { // Don't allow opening screen from another world
            return false;
        }

        final var event = dispatchEvent(ctx -> new VoidPrePlayerOpenBlockScreenEvent(ctx, info.player(), info.blockEntity()));
        if (event == null) return true;

        return !event.isCanceled();
    }

    public static void postPlayerOpenBlockScreen(BlockEntityScreenInfo info) {
        dispatchEvent(ctx -> new VoidPostPlayerOpenBlockScreenEvent(ctx, info.player(), info.blockEntity()));
    }
}
