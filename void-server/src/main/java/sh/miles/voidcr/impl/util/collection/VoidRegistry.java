package sh.miles.voidcr.impl.util.collection;

import com.google.common.base.Preconditions;
import sh.miles.voidcr.util.collection.KeyHolder;
import sh.miles.voidcr.util.collection.Registry;
import sh.miles.voidcr.util.collection.exception.RegistryValueNotFoundException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VoidRegistry<E extends KeyHolder<K>, K> implements Registry<E, K> {

    private final Map<K, E> registry;
    private boolean frozen;

    public VoidRegistry() {
        this.registry = new HashMap<>();
        this.frozen = false;
    }

    @Override
    public E get(final K key) {
        Preconditions.checkArgument(key != null, "The provided registry ke must not be null");
        return this.registry.get(key);
    }

    @Override
    public E getOrThrow(final K key) throws RegistryValueNotFoundException {
        Preconditions.checkArgument(key != null, "The provided key must not be null");
        final var fetch = this.registry.get(key);
        if (fetch == null) throw new RegistryValueNotFoundException(key);
        return fetch;
    }

    @Override
    public void register(E entry) {
        if (isFrozen()) throw new IllegalStateException("Can not register while registry is frozen");
        this.registry.put(entry.key(), entry);
    }

    @Override
    public boolean isFrozen() {
        return this.frozen;
    }

    @Override
    public Iterator<E> iterator() {
        return this.registry.values().iterator();
    }

    public void freeze() {
        this.frozen = true;
    }
}
