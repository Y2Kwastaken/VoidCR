package sh.miles.voidcr.impl.util;

import com.google.common.collect.ImmutableMap;
import finalforeach.cosmicreach.entities.EntityUniqueId;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.impl.entity.VoidEntityIdentifier;
import sh.miles.voidcr.util.MagicMethods;
import sh.miles.voidcr.util.collection.Pair;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public final class VoidMagicMethods implements MagicMethods {

    private static final Map<Class<?>, Pair<Supplier<ICRBinSerializable>, Function<ICRBinSerializable, Object>>> DESERIALIZABLES = ImmutableMap.<Class<?>, Pair<Supplier<ICRBinSerializable>, Function<ICRBinSerializable, Object>>>builder()
            .put(EntityIdentifier.class, new Pair<>(EntityUniqueId::getNew, VoidEntityIdentifier::new))
            .build();

    @Override
    public <T> T deserialize(final Class<T> clazz, final byte[] bytes) throws IllegalArgumentException {
        final Pair<Supplier<ICRBinSerializable>, Function<ICRBinSerializable, Object>> context = DESERIALIZABLES.get(clazz);
        if (context == null) {
            throw new IllegalArgumentException("Invalid Cosmic Reach class %s".formatted(clazz.getName()));
        }

        return clazz.cast(context.right().apply(deserialize(context.left(), bytes)));
    }

    public static byte[] serialize(ICRBinSerializable serializable) {
        final CRBinSerializer serializer = CRBinSerializer.getNew();
        serializable.write(serializer);
        return serializer.toBytes();
    }

    private static <T extends ICRBinSerializable> T deserialize(final Supplier<T> object, final byte[] bytes) {
        final T deserialized = object.get();
        deserialized.read(prepareDeserializer(bytes));
        return deserialized;
    }

    private static CRBinDeserializer prepareDeserializer(final byte[] bytes) {
        final CRBinDeserializer deserializer = CRBinDeserializer.getNew();
        deserializer.prepareForRead(ByteBuffer.wrap(bytes));
        return deserializer;
    }
}
