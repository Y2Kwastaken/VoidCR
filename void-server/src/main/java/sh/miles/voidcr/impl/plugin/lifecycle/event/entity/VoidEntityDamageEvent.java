package sh.miles.voidcr.impl.plugin.lifecycle.event.entity;

import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.plugin.lifecycle.event.entity.EntityDamageEvent;
import sh.miles.voidcr.server.Server;

public abstract class VoidEntityDamageEvent extends VoidEntityEvent implements EntityDamageEvent {

    protected int invulnerabilityFrames;
    protected float damage;
    private final Entity damager;

    public VoidEntityDamageEvent(final Server ctx, final finalforeach.cosmicreach.entities.Entity entity, final Zone zone, final int invulnerabilityFrames, final float damage, final finalforeach.cosmicreach.entities.Entity damager) {
        super(ctx, entity, zone);
        this.invulnerabilityFrames = invulnerabilityFrames;
        this.damage = damage;
        this.damager = damager == null ? null : damager.getVoidMirror();
    }

    @Override
    public int getInvulnerabilityFrames() {
        return this.invulnerabilityFrames;
    }

    @Override
    public float getDamage() {
        return this.damage;
    }

    @Override
    public Entity getDamager() {
        return this.damager;
    }
}
