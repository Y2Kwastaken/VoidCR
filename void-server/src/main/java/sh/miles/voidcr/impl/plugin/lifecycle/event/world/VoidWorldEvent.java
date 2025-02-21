package sh.miles.voidcr.impl.plugin.lifecycle.event.world;

import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.impl.plugin.lifecycle.event.VoidLifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.WorldEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.World;

public abstract class VoidWorldEvent extends VoidLifecycleEvent<Server> implements WorldEvent {
    private final World world;

    public VoidWorldEvent(final Server context, final Zone zone) {
        super(context);
        this.world = zone.getVoidMirror();
    }

    @Override
    public World getWorld() {
        return world;
    }
}
