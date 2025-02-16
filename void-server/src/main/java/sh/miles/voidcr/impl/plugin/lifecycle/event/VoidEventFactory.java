package sh.miles.voidcr.impl.plugin.lifecycle.event;

import sh.miles.voidcr.impl.plugin.lifecycle.event.entity.post.VoidPostEntityDamageEvent;
import sh.miles.voidcr.impl.plugin.lifecycle.event.entity.pre.VoidPreEntityDamageEvent;

import static sh.miles.voidcr.impl.plugin.lifecycle.VoidLifecycleManager.dispatchEvent;

public final class VoidEventFactory {

    private VoidEventFactory() {
        throw new UnsupportedOperationException("Can not create instance of utility class VoidEventFactory");
    }

    public static VoidPreEntityDamageEvent preEntityDamageEvent(float damage, int invulnerabilityFrames, finalforeach.cosmicreach.entities.Entity damager, finalforeach.cosmicreach.entities.Entity effected) {
        return dispatchEvent(ctx -> new VoidPreEntityDamageEvent(
                ctx, effected, effected.zone, invulnerabilityFrames, damage, damager
        ));
    }

    public static void postEntityDamageEvent(float damage, int invulnerabilityFrames, finalforeach.cosmicreach.entities.Entity damager, finalforeach.cosmicreach.entities.Entity effected) {
        dispatchEvent(ctx -> new VoidPostEntityDamageEvent(
                ctx, effected, effected.zone, invulnerabilityFrames, damage, damager
        ));
    }
}
