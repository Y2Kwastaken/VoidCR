package sh.miles.voidcr.util.serialize;

/**
 * Marks an object as byte serializable. All objects marked with byte serializable should conform to the following
 * format. Exactly one implementation of the toBytes() provided by this interface and one static method with the
 * signature {@code static YourObject fromBytes(byte[] input)}.
 *
 * @since 0.3.14
 */
public interface ByteSerializable {
    byte[] toBytes();
}
