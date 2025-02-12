package sh.miles.voidcr.impl.plugin.lifecycle.event.entity;

import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.impl.plugin.lifecycle.event.VoidLifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.entity.EntityEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.World;

public abstract class VoidEntityEvent extends VoidLifecycleEvent<Server> implements EntityEvent {

    protected final sh.miles.voidcr.entity.Entity entity;
    protected final World world;

    public VoidEntityEvent(final Server ctx, Entity entity, Zone zone) {
        super(ctx);
        this.entity = entity.getVoidMirror();
        this.world = zone.getVoidMirror();
    }

    @Override
    public sh.miles.voidcr.entity.Entity getEntity() {
        return this.entity;
    }

    @Override
    public World getWorld() {
        return this.world;
    }
}
