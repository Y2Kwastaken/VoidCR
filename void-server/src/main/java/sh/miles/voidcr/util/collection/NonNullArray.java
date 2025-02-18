package sh.miles.voidcr.util.collection;

import java.util.AbstractList;
import java.util.function.Function;

public final class NonNullArray<E> extends AbstractList<E> {

    private final Object[] array;
    private final Function<Integer, E> nullCase;

    public NonNullArray(int size, Function<Integer, E> nullCase) {
        array = new Object[size];
        this.nullCase = nullCase;
    }

    @Override
    public boolean add(final E e) {
        throw new UnsupportedOperationException("NonNullArray does not support List#add");
    }

    @Override
    public E get(final int index) {
        return (E) array[index];
    }

    @Override
    public E set(final int index, E element) {
        if (element == null) {
            element = nullCase.apply(index);
        }

        var prev = array[index];
        array[index] = element;
        return (E) prev;
    }

    @Override
    public int size() {
        return array.length;
    }
}
