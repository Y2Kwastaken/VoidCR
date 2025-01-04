package sh.miles.voidcr.entity;

import sh.miles.voidcr.server.VoidCR;
import sh.miles.voidcr.util.serialize.ByteSerializable;

/**
 * Marks an EntityIdentifier for equality checks
 *
 * @since 0.3.14
 */
public interface EntityIdentifier extends ByteSerializable {

    static EntityIdentifier fromBytes(byte[] input) {
        return VoidCR.getMagic().deserialize(EntityIdentifier.class, input);
    }
}
