package sh.miles.voidcr.impl.world.position;

import sh.miles.voidcr.util.function.TripleDoubleFunction;
import sh.miles.voidcr.world.position.DecimalPosition;

public abstract class VoidDecimalPosition<T extends DecimalPosition<T>> implements DecimalPosition<T> {

    protected final double x;
    protected final double y;
    protected final double z;

    public VoidDecimalPosition(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public T withChanges(final TripleDoubleFunction<T> changes) {
        return changes.compute(x, y, z);
    }

    @Override
    public T add(final double x, final double y, final double z) {
        return create(this.x + x, this.y + y, this.z + z, others());
    }

    @Override
    public T multiply(final double x, final double y, final double z) {
        return create(this.x * x, this.y * y, this.z * z, others());
    }

    @Override
    public T divide(final double x, final double y, final double z) {
        return create(this.x / x, this.y / y, this.z / z, others());
    }

    public abstract T create(double x, double y, double z, Object[] other);

    protected abstract Object[] others();
}
