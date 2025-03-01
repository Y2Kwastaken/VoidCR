package sh.miles.voidcr.server.registry;

import sh.miles.voidcr.util.Keyed;
import sh.miles.voidcr.util.NamedKey;
import sh.miles.voidcr.util.collection.Registry;

/**
 * A registry which could, potentially be modified and is used to store items.
 *
 * @param <E> the registry value type
 * @since 0.3.14
 */
public interface NamedRegistry<E extends Keyed> extends Registry<E, NamedKey>, Iterable<E> {
}
