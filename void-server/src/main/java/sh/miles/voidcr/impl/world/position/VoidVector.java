package sh.miles.voidcr.impl.world.position;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import sh.miles.voidcr.impl.util.VoidMagicMethods;
import sh.miles.voidcr.util.CRSerializerHelper;
import sh.miles.voidcr.world.position.Vector;

public class VoidVector extends VoidDecimalPosition<Vector> implements Vector {

    private static final float PI2 = (float) (Math.PI / 2);

    public static Vector fromVector3(Vector3 vector3) {
        return new VoidVector(vector3.x, vector3.y, vector3.z);
    }

    public static Vector3 toVector3(Vector vector) {
        return new Vector3(vector.x(), vector.y(), vector.z());
    }

    public VoidVector(final float x, final float y, final float z) {
        super(x, y, z);
    }

    @Override
    public Vector create(final float x, final float y, final float z, final Object[] other) {
        return new VoidVector(x, y, z);
    }

    @Override
    protected Object[] others() {
        return new Object[0];
    }

    @Override
    public Vector negate() {
        return new VoidVector(-this.x, -this.y, -this.z);
    }

    @Override
    public Vector add(final Vector vector) {
        return new VoidVector(this.x + vector.x(), this.y + vector.y(), this.z + vector.z());
    }

    @Override
    public Vector subtract(final Vector vector) {
        return new VoidVector(this.x - vector.x(), this.y - vector.y(), this.z - vector.z());
    }

    @Override
    public Vector multiply(final Vector vector) {
        return new VoidVector(this.x * vector.x(), this.y * vector.y(), this.z * vector.z());
    }

    @Override
    public Vector multiply(final float scalar) {
        return new VoidVector(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    @Override
    public Vector rotateX(final float angle) {
        double sin = Math.sin(angle);
        double cos = cosFromSin(sin, angle);
        final var ny = this.y * cos - this.z * sin;
        final var nz = this.y * sin + this.z * cos;
        return new VoidVector(this.x, (float) ny, (float) nz);
    }

    @Override
    public Vector rotateY(final float angle) {
        double sin = Math.sin(angle);
        double cos = cosFromSin(sin, angle);
        final var nx = this.x * cos + this.z * sin;
        final var nz = -this.x * sin + this.z * cos;
        return new VoidVector((float) nx, this.y, (float) nz);
    }

    @Override
    public Vector rotateZ(final float angle) {
        double sin = Math.sin(angle);
        double cos = cosFromSin(sin, angle);
        final var nx = this.x * cos - this.y * sin;
        final var ny = this.x * sin + this.y * cos;

        return new VoidVector((float) nx, (float) ny, this.z);
    }

    @Override
    public Vector midpoint(final Vector vector) {
        return new VoidVector(
                (x + vector.x()) / 2,
                (y + vector.y()) / 2,
                (z + vector.z()) / 2
        );
    }

    @Override
    public Vector normalize() {
        final var magnitude = magnitude();
        return new VoidVector(this.x / magnitude, this.y / magnitude, this.z / magnitude);
    }

    @Override
    public float angle(final Vector vector) {
        final var am = magnitude();
        final var bm = vector.magnitude();
        final var rslt = Math.acos(dot(vector) / (am * bm));

        return (float) Math.acos(dot(vector) / (am * bm));
    }

    @Override
    public float dot(final Vector vector) {
        return this.x * vector.x() + this.y * vector.y() + this.z * vector.z();
    }

    @Override
    public Vector cross(final Vector vector) {
        var nx = this.y * vector.z() - vector.y() * this.z;
        var ny = this.z * vector.x() - vector.z() * this.x;
        var nz = this.x * vector.y() - vector.x() * this.y;
        return new VoidVector(nx, ny, nz);
    }

    @Override
    public float magnitude() {
        return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    @Override
    public String toString() {
        return "VoidVector" +
                "< " + x +
                ", " + y +
                ", " + z +
                " >";
    }

    private static double cosFromSin(double sin, double angle) {
        double cos = Math.sqrt(1.0 - sin * sin);
        double a = angle + PI2;
        double b = a - (int) (a / PI2) * PI2;
        if (b < 0.0) {
            b = PI2 + b;
        }
        if (b >= Math.PI) {
            return -cos;
        }
        return cos;
    }

    @Override
    public byte[] toBytes() {
        final CRSerializerHelper.CRBinSerializerWrapper<Vector> wrapped = CRSerializerHelper.create(Vector.class);
        wrapped.setResult(this);
        return VoidMagicMethods.serialize(wrapped);
    }

    public static VoidVector deserialize(ICRBinSerializable serializable) {
        if (serializable instanceof CRSerializerHelper.CRBinSerializerWrapper<?> wrapper) {
            return (VoidVector) wrapper.getResult();
        }

        throw new IllegalArgumentException("Unexpected serializable class " + serializable.getClass());
    }
}
