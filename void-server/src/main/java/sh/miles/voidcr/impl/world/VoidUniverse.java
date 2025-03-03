package sh.miles.voidcr.impl.world;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.world.World;
import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.impl.util.VoidNamedKey;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.world.Universe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class VoidUniverse implements Universe, Mirrored<World> {

    private final World mirror;
    private final NamedKey defaultWorldKey;

    public VoidUniverse(World mirror) {
        this.mirror = mirror;
        this.defaultWorldKey = NamedKey.key(mirror.defaultZoneId);
    }

    @Override
    public World getMirror() {
        return this.mirror;
    }

    @Override
    public void setTime(final long time) {
        Preconditions.checkArgument(time >= 0, "The provided time must be greater than or equal to 0");
        mirror.currentWorldTick = time;
    }

    @Override
    public long getTime() {
        return mirror.currentWorldTick;
    }

    @Override
    public sh.miles.voidcr.world.World getWorld(final NamedKey key) {
        Preconditions.checkArgument(key != null, "The provided key must not be null");
        final var zone = mirror.getZoneIfExists(((VoidNamedKey) key).getCosmicReachId());
        return zone == null ? null : zone.getVoidMirror();
    }

    @Override
    public Collection<sh.miles.voidcr.world.World> getWorlds() {
        final var zones = mirror.getZones();
        final List<sh.miles.voidcr.world.World> collector = new ArrayList<>(zones.size());
        for (final Zone zone : zones) {
            collector.add(zone.getVoidMirror());
        }
        return collector;
    }

    @Override
    public long getSeed() {
        return mirror.worldSeed;
    }

    @Override
    public NamedKey getDefaultWorldKey() {
        return this.defaultWorldKey;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final VoidUniverse that)) return false;
        return Objects.equals(mirror, that.mirror);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mirror);
    }
}
