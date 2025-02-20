package sh.miles.voidcr.impl.plugin.lifecycle.event;

import com.badlogic.gdx.graphics.Color;
import finalforeach.cosmicreach.blockentities.BlockEntitySign;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import sh.miles.voidcr.impl.plugin.lifecycle.event.entity.post.VoidPostEntityDamageEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.entity.pre.VoidPreEntityDamageEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.post.VoidPostPlayerSignUpdateEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.pre.VoidPrePlayerSignUpdateEvent;

import static sh.miles.voidcr.impl.plugin.lifecycle.VoidLifecycleManager.dispatchEvent;

public final class VoidEventFactory {

    private VoidEventFactory() {
        throw new UnsupportedOperationException("Can not create instance of utility class VoidEventFactory");
    }

    public static VoidPreEntityDamageEvent preEntityDamageEvent(float damage, int invulnerabilityFrames, finalforeach.cosmicreach.entities.Entity damager, finalforeach.cosmicreach.entities.Entity effected) {
        return dispatchEvent(ctx -> new VoidPreEntityDamageEvent(ctx, effected, effected.zone, invulnerabilityFrames, damage, damager));
    }

    public static void postEntityDamageEvent(float damage, int invulnerabilityFrames, finalforeach.cosmicreach.entities.Entity damager, finalforeach.cosmicreach.entities.Entity effected) {
        dispatchEvent(ctx -> new VoidPostEntityDamageEvent(ctx, effected, effected.zone, invulnerabilityFrames, damage, damager));
    }

    public static VoidPrePlayerSignUpdateEvent prePlayerSignUpdateEvent(NetworkIdentity actor, BlockEntitySign sign, String[] lines, float fontSize, int color) {
        return dispatchEvent(ctx -> new VoidPrePlayerSignUpdateEvent(ctx, sign, lines, new Color(color), fontSize, actor.getPlayer()));
    }

    public static void postPlayerSignUpdateEvent(NetworkIdentity actor, BlockEntitySign sign) {
        dispatchEvent(ctx -> new VoidPostPlayerSignUpdateEvent(ctx, sign, actor.getPlayer()));
    }
}
