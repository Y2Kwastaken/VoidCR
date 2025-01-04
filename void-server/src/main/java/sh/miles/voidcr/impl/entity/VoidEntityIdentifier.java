package sh.miles.voidcr.impl.entity;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.entities.EntityUniqueId;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.impl.util.VoidMagicMethods;

import java.util.Objects;

public final class VoidEntityIdentifier implements EntityIdentifier, Mirrored<EntityUniqueId> {

    private final EntityUniqueId mirror;

    public VoidEntityIdentifier(ICRBinSerializable serializable) {
        Preconditions.checkArgument(serializable instanceof EntityUniqueId, "The provided serializable must be of the tpe EntityUniqueId");
        this.mirror = (EntityUniqueId) serializable;
    }

    public VoidEntityIdentifier(EntityUniqueId mirror) {
        this.mirror = mirror;
    }

    @Override
    public byte[] toBytes() {
        return VoidMagicMethods.serialize(this.mirror);
    }

    @Override
    public EntityUniqueId getMirror() {
        return this.mirror;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final VoidEntityIdentifier that)) return false;
        return Objects.equals(mirror, that.mirror);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mirror);
    }
}
