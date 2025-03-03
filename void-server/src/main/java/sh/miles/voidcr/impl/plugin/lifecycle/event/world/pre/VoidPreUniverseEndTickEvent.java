package sh.miles.voidcr.impl.plugin.lifecycle.event.world.pre;

import finalforeach.cosmicreach.world.World;
import sh.miles.voidcr.impl.plugin.lifecycle.event.VoidLifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.pre.PreUniverseTickEndEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.Universe;

public class VoidPreUniverseEndTickEvent extends VoidLifecycleEvent<Server> implements PreUniverseTickEndEvent {

    private final Universe universe;
    private long time;

    public VoidPreUniverseEndTickEvent(Server server, World world, long time) {
        super(server);
        this.universe = world.getVoidMirror();
        this.time = time;
    }

    @Override
    public long getCurrentTime() {
        return this.time;
    }

    @Override
    public Universe getUniverse() {
        return this.universe;
    }

    @Override
    public Class<? extends LifecycleEvent<Server>> getEventClass() {
        return PreUniverseTickEndEvent.class;
    }
}
