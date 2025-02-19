package sh.miles.voidcr.util;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import sh.miles.voidcr.impl.world.position.VoidBlockPos;
import sh.miles.voidcr.world.position.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CRSerializerHelper {

    private static final Map<Class<?>, Supplier<CRBinSerializerWrapper<?>>> serializer = new HashMap<>();

    static {
        serializer.put(BlockPos.class, () -> new CRBinSerializerWrapper<BlockPos>() {

            @Override
            public void read(final CRBinDeserializer deserializer) {
                int[] array = deserializer.readIntArray("xyz");
                super.result = new VoidBlockPos(array[0], array[1], array[2]);
            }

            @Override
            public void write(final CRBinSerializer serializer) {
                if (super.result == null) {
                    serializer.writeNullIntArray("xyz");
                    return;
                }

                serializer.writeIntArray("xyz", new int[]{super.result.x(), super.result.y(), super.result.z()});
            }

            @Override
            public BlockPos getResult() throws IllegalStateException {
                Preconditions.checkState(super.result != null, "Can not retrieve result from CRBinSerializerWrapper because it has not been read yet");
                return super.result;
            }
        });
    }

    public static <T> CRBinSerializerWrapper<T> create(Class<T> clazz) {
        return (CRBinSerializerWrapper<T>) serializer.get(clazz).get();
    }

    public static abstract class CRBinSerializerWrapper<R> implements ICRBinSerializable {
        protected R result;

        public void setResult(R result) {
            this.result = result;
        }

        public abstract R getResult() throws IllegalStateException;
    }
}
