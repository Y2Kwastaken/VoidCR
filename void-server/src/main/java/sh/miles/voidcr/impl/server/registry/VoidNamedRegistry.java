package sh.miles.voidcr.impl.server.registry;

import com.badlogic.gdx.utils.Array;
import sh.miles.voidcr.impl.util.collection.VoidRegistry;
import sh.miles.voidcr.server.registry.NamedRegistry;
import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.NamedKey;

import java.util.Collection;
import java.util.function.Function;

public final class VoidNamedRegistry<E extends Keyed> extends VoidRegistry<E, NamedKey> implements NamedRegistry<E> {

    public static <E extends Keyed, I> VoidNamedRegistry<E> fromNaiveInternalSource(Collection<I> provision, Function<I, E> map) {
        final VoidNamedRegistry<E> registry = new VoidNamedRegistry<>();
        for (final I entry : provision) {
            registry.register(map.apply(entry));
        }

        registry.freeze();
        return registry;
    }

    public static <E extends Keyed, I> VoidNamedRegistry<E> fromVoidCRArraySource(I[] provision, Function<I, E> map) {
        final VoidNamedRegistry<E> registry = new VoidNamedRegistry<>();
        for (final I entry : provision) {
            registry.register(map.apply(entry));
        }

        registry.freeze();
        return registry;
    }

    public static <E extends Keyed, I> VoidNamedRegistry<E> fromGdxNaiveInternalSource(Array<I> provision, Function<I, E> map) {
        final VoidNamedRegistry<E> registry = new VoidNamedRegistry<>();
        for (final I entry : provision) {
            registry.register(map.apply(entry));
        }

        registry.freeze();
        return registry;
    }
}
