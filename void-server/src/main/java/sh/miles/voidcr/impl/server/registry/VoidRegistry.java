package sh.miles.voidcr.impl.server.registry;

import com.badlogic.gdx.utils.Array;
import com.google.common.base.Preconditions;
import org.checkerframework.checker.units.qual.K;
import sh.miles.voidcr.server.registry.Registry;
import sh.miles.voidcr.server.registry.exception.RegistryValueNotFoundException;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.NamedKey;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

public final class VoidRegistry<E extends Keyed> implements Registry<E> {

    private final Map<NamedKey, E> registry;
    private boolean frozen;

    public VoidRegistry() {
        this.registry = new HashMap<>();
        this.frozen = false;
    }

    @Override
    public E get(final NamedKey key) {
        Preconditions.checkArgument(key != null, "The provided registry ke must not be null");
        return this.registry.get(key);
    }

    @Override
    public E getOrThrow(final NamedKey key) throws RegistryValueNotFoundException {
        Preconditions.checkArgument(key != null, "The provided key must not be null");
        final var fetch = this.registry.get(key);
        if (fetch == null) throw new RegistryValueNotFoundException(key);
        return fetch;
    }

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

    public static <E extends Keyed, I> VoidRegistry<E> fromNaiveInternalSource(Collection<I> provision, Function<I, E> map) {
        final VoidRegistry<E> registry = new VoidRegistry<>();
        for (final I entry : provision) {
            registry.register(map.apply(entry));
        }

        registry.freeze();
        return registry;
    }

    public static <E extends Keyed, I> VoidRegistry<E> fromVoidCRArraySource(I[] provision, Function<I, E> map) {
        final VoidRegistry<E> registry = new VoidRegistry<>();
        for (final I entry : provision) {
            registry.register(map.apply(entry));
        }

        registry.freeze();
        return registry;
    }

    public static <E extends Keyed, I> VoidRegistry<E> fromGdxNaiveInternalSource(Array<I> provision, Function<I, E> map) {
        final VoidRegistry<E> registry = new VoidRegistry<>();
        for (final I entry : provision) {
            registry.register(map.apply(entry));
        }

        registry.freeze();
        return registry;
    }
}
