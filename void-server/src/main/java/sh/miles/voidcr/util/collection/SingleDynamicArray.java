package sh.miles.voidcr.util.collection;

import com.google.common.collect.Iterators;
import finalforeach.cosmicreach.savelib.utils.IDynamicArray;

import java.util.Iterator;

public class SingleDynamicArray<E> implements IDynamicArray<E> {

    private final E content;
    public SingleDynamicArray(E content) {
        this.content = content;
    }

    @Override
    public void add(final E var1) {
        throw new UnsupportedOperationException("Can not add items in a SingleDynamicArray");
    }

    @Override
    public E get(final int var1) {
        if (var1 > 0) {
            throw new UnsupportedOperationException("Can not get non first items from SingleDynamicArray");
        }
        return this.content;
    }

    @Override
    public boolean contains(final E var1, final boolean var2) {
        return var1 == this.content;
    }

    @Override
    public int indexOf(final E var1, final boolean var2) {
        return var1 == this.content ? 1 : -1;
    }

    @Override
    public E[] items() {
        throw new UnsupportedOperationException("Can not get array of items in a SingleDynamicArray");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Can not clear items in a SingleDynamicArray");
    }

    @Override
    public E removeIndex(final int var1) {
        throw new UnsupportedOperationException("Can not remove items in a SingleDynamicArray");
    }

    @Override
    public Iterator<E> iterator() {
        return Iterators.forArray(this.content);
    }

    @Override
    public int size() {
        return 1;
    }
}
