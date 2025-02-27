package sh.miles.voidcr.impl.world.position;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import sh.miles.voidcr.impl.util.VoidMagicMethods;
import sh.miles.voidcr.util.CRSerializerHelper;
import sh.miles.voidcr.world.position.BlockPos;
import sh.miles.voidcr.world.position.Position;

public class VoidPosition extends VoidDecimalPosition<Position> implements Position {

    public static VoidPosition fromVector3(Vector3 vector3) {
        return new VoidPosition(vector3.x, vector3.y, vector3.z);
    }

    public static Vector3 toVector3(Position position) {
        return new Vector3(position.x(), position.y(), position.z());
    }

    public VoidPosition(final float x, final float y, final float z) {
        super(x, y, z);
    }

    @Override
    public BlockPos coerce() {
        return new VoidBlockPos((int) x, (int) y, (int) z);
    }

    @Override
    public Position create(final float x, final float y, final float z, final Object[] other) {
        return new VoidPosition(x, y, z);
    }

    @Override
    protected Object[] others() {
        return new Object[0];
    }

    @Override
    public byte[] toBytes() {
        final CRSerializerHelper.CRBinSerializerWrapper<Position> wrapped = CRSerializerHelper.create(Position.class);
        wrapped.setResult(this);
        return VoidMagicMethods.serialize(wrapped);
    }

    public static VoidPosition deserialize(ICRBinSerializable serializable) {
        if (serializable instanceof CRSerializerHelper.CRBinSerializerWrapper<?> wrapper) {
            return (VoidPosition) wrapper.getResult();
        }

        throw new IllegalArgumentException("Unexpected serializable class " + serializable.getClass());
    }
}
