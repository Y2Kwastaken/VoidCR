package sh.miles.voidcr.impl.plugin.lifecycle.event.entity.pre;

import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.impl.plugin.lifecycle.event.entity.VoidEntityDamageEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.entity.pre.PreEntityDamageEvent;
import sh.miles.voidcr.server.Server;

public class VoidPreEntityDamageEvent extends VoidEntityDamageEvent implements PreEntityDamageEvent {

    public VoidPreEntityDamageEvent(final Server ctx, final Entity entity, final Zone zone, final int invulnerabilityFrames, final float damage, final Entity damager) {
        super(ctx, entity, zone, invulnerabilityFrames, damage, damager);
    }

    @Override
    public void setInvulnerabilityFrames(final int invulnerabilityFrames) {
        super.invulnerabilityFrames = invulnerabilityFrames;
    }

    @Override
    public void setDamage(final float pendingDamage) {
        super.damage = pendingDamage;
    }

    @Override
    public Class<? extends LifecycleEvent<Server>> getEventClass() {
        return PreEntityDamageEvent.class;
    }
}
