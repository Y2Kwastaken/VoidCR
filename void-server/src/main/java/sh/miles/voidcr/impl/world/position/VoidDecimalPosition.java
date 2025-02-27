package sh.miles.voidcr.impl.world.position;

import sh.miles.voidcr.util.function.TripleFloatFunction;
import sh.miles.voidcr.world.position.DecimalPosition;

import java.util.Objects;

public abstract class VoidDecimalPosition<T extends DecimalPosition<T>> implements DecimalPosition<T> {

    protected final float x;
    protected final float y;
    protected final float z;

    public VoidDecimalPosition(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public float x() {
        return this.x;
    }

    @Override
    public float y() {
        return this.y;
    }

    @Override
    public float z() {
        return this.z;
    }

    @Override
    public T withChanges(final TripleFloatFunction<T> changes) {
        return changes.compute(x, y, z);
    }

    @Override
    public T add(final float x, final float y, final float z) {
        return create(this.x + x, this.y + y, this.z + z, others());
    }

    @Override
    public T multiply(final float x, final float y, final float z) {
        return create(this.x * x, this.y * y, this.z * z, others());
    }

    @Override
    public T divide(final float x, final float y, final float z) {
        return create(this.x / x, this.y / y, this.z / z, others());
    }

    public abstract T create(float x, float y, float z, Object[] other);

    protected abstract Object[] others();

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final VoidDecimalPosition<?> that)) return false;
        return Float.compare(x, that.x) == 0 && Float.compare(y, that.y) == 0 && Float.compare(z, that.z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
