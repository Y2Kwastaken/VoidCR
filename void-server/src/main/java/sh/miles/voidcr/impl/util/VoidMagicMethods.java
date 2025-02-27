package sh.miles.voidcr.impl.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import finalforeach.cosmicreach.entities.EntityUniqueId;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.impl.entity.VoidEntityIdentifier;
import sh.miles.voidcr.impl.server.registry.VoidRegistries;
import sh.miles.voidcr.impl.world.inventory.item.VoidItemKey;
import sh.miles.voidcr.impl.world.inventory.item.VoidItemStack;
import sh.miles.voidcr.impl.world.inventory.item.VoidItemType;
import sh.miles.voidcr.impl.world.position.VoidBlockPos;
import sh.miles.voidcr.impl.world.position.VoidPosition;
import sh.miles.voidcr.impl.world.position.VoidVector;
import sh.miles.voidcr.server.registry.Registry;
import sh.miles.voidcr.util.CRSerializerHelper;
import sh.miles.voidcr.util.CRSerializerHelper.CRBinSerializerWrapper;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.MagicMethods;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.util.collection.Pair;
import sh.miles.voidcr.world.inventory.item.ItemKey;
import sh.miles.voidcr.world.inventory.item.ItemStack;
import sh.miles.voidcr.world.inventory.item.ItemType;
import sh.miles.voidcr.world.position.BlockPos;
import sh.miles.voidcr.world.position.Position;
import sh.miles.voidcr.world.position.Vector;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public final class VoidMagicMethods implements MagicMethods {

    private static final Map<Class<?>, Pair<Supplier<ICRBinSerializable>, Function<ICRBinSerializable, Object>>> DESERIALIZABLES = ImmutableMap.<Class<?>, Pair<Supplier<ICRBinSerializable>, Function<ICRBinSerializable, Object>>>builder()
            .put(EntityIdentifier.class, new Pair<>(EntityUniqueId::getNew, VoidEntityIdentifier::new))
            .put(BlockPos.class, new Pair<>(() -> CRSerializerHelper.create(BlockPos.class), VoidBlockPos::deserialize))
            .put(Position.class, new Pair<>(() -> CRSerializerHelper.create(Position.class), VoidPosition::deserialize))
            .put(Vector.class, new Pair<>(() -> CRSerializerHelper.create(Vector.class), VoidVector::deserialize))
            .build();

    @Override
    public <T> T deserialize(final Class<T> clazz, final byte[] bytes) throws IllegalArgumentException {
        Preconditions.checkArgument(clazz != null, "The provided class must not be null");
        Preconditions.checkArgument(bytes != null, "The provided bytes must not be null");

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

    @Override
    public NamedKey createNamedKey(final String namespace, final String key) throws IllegalArgumentException {
        return new VoidNamedKey(namespace, key);
    }

    @Override
    public NamedKey createNamedKey(final String keyString) throws IllegalArgumentException {
        final String[] split = keyString.split(":");
        Preconditions.checkArgument(split.length == 2, "keyString must be splittable by ':'. key %s is not splittable array results in %s".formatted(keyString, Arrays.toString(split)));

        return new VoidNamedKey(split[0], split[1]);
    }

    @Override
    public ItemKey createItemKey(final String namespace, final String key) throws IllegalArgumentException {
        return new VoidItemKey(namespace, key);
    }

    @Override
    public ItemKey createItemKey(final String keyString) throws IllegalArgumentException {
        final String[] split = keyString.split(":");
        Preconditions.checkArgument(split.length == 2, "keyString must be splittable by ':'. key %s is not splittable array results in %s".formatted(keyString, Arrays.toString(split)));

        return new VoidItemKey(split[0], split[1]);
    }

    @Override
    public ItemStack createItemStack(final ItemType itemType) throws IllegalArgumentException {
        return new VoidItemStack(new finalforeach.cosmicreach.items.ItemStack(((VoidItemType) itemType).getMirror()));
    }

    @Override
    public BlockPos createBlockPos(final int x, final int y, final int z) throws IllegalArgumentException {
        return new VoidBlockPos(x, y, z);
    }

    @Override
    public Position createPosition(final float x, final float y, final float z) throws IllegalArgumentException {
        return new VoidPosition(x, y, z);
    }

    @Override
    public Vector createVector(final float x, final float y, final float z) throws IllegalArgumentException {
        return new VoidVector(x, y, z);
    }

    @Override
    public <E extends Keyed> Registry<E> getRegistry(final Class<E> clazz) throws IllegalArgumentException {
        return (Registry<E>) VoidRegistries.createRegistry(clazz);
    }
}
