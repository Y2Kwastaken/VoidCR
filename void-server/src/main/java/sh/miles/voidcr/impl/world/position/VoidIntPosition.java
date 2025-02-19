package sh.miles.voidcr.impl.world.position;

import sh.miles.voidcr.util.function.TripleIntFunction;
import sh.miles.voidcr.world.position.IntPosition;

public abstract class VoidIntPosition<T extends IntPosition<T>> implements IntPosition<T> {

    protected final int x;
    protected final int y;
    protected final int z;

    public VoidIntPosition(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int x() {
        return this.x;
    }

    @Override
    public int y() {
        return this.y;
    }

    @Override
    public int z() {
        return this.z;
    }

    @Override
    public T withChanges(final TripleIntFunction<T> changes) {
        return changes.compute(x, y, z);
    }

    @Override
    public T add(final int x, final int y, final int z) {
        return create(this.x * x, this.y * y, this.z * z, others());
    }

    @Override
    public T multiply(final int x, final int y, final int z) {
        return create(this.x * x, this.y * y, this.z * z, others());
    }

    @Override
    public T divide(final int x, final int y, final int z) {
        return create(this.x * x, this.y * y, this.z * z, others());
    }

    public abstract T create(int x, int y, int z, Object[] other);

    protected abstract Object[] others();
}
