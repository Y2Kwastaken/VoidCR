package sh.miles.voidcr.impl.plugin.lifecycle.event.entity;

import finalforeach.cosmicreach.networking.packets.entities.HitEntityPacket;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.impl.entity.VoidEntity;
import sh.miles.voidcr.impl.plugin.lifecycle.VoidLifecycleManager;
import sh.miles.voidcr.impl.world.VoidWorld;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.entity.PreEntityDamageEvent;
import sh.miles.voidcr.server.Server;

public class VoidPreEntityDamageEvent extends VoidEntityEvent implements PreEntityDamageEvent {

    private final Server context;
    private final Entity damager;
    private float damage;
    private int invulnerabilityFrames;

    public VoidPreEntityDamageEvent(final Server context, final float damage, final int invulnerabilityFrames, final finalforeach.cosmicreach.entities.Entity damager, final finalforeach.cosmicreach.entities.Entity entity, final Zone zone) {
        super(context, entity, zone);
        this.damager = damager == null ? null : damager.getVoidMirror();
        this.context = context;
        this.damage = damage;
        this.invulnerabilityFrames = invulnerabilityFrames;
    }

    @Override
    public int getInvulnerabilityFrames() {
        return this.invulnerabilityFrames;
    }

    @Override
    public void setInvulnerabilityFrames(int invulnerabilityFrames) {
        this.invulnerabilityFrames = invulnerabilityFrames;
    }

    @Override
    public float getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(final float damage) {
        this.damage = damage;
    }

    @Override
    public Entity getDamager() {
        return this.damager;
    }

    @Override
    public Server getContext() {
        return this.context;
    }

    @Override
    public Class<? extends LifecycleEvent<Server>> getEventClass() {
        return PreEntityDamageEvent.class;
    }

    public static VoidPreEntityDamageEvent dispatch(float damage, int invulnerabilityFrames, finalforeach.cosmicreach.entities.Entity damager, finalforeach.cosmicreach.entities.Entity effected) {
        return VoidLifecycleManager.callServerEvent((ctx) -> new VoidPreEntityDamageEvent(
                ctx, damage, invulnerabilityFrames, damager, effected, effected.zone
        ));
    }

    public static void dispatchUpdates(VoidPreEntityDamageEvent event) {
        ServerSingletons.SERVER.broadcast(((VoidWorld) event.getWorld()).getMirror(), new HitEntityPacket(((VoidEntity) event.getEntity()).getMirror(), event.getDamage()));
    }
}
