package sh.miles.voidcr.impl.plugin.lifecycle;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.plugin.lifecycle.LifecycleAware;
import sh.miles.voidcr.plugin.lifecycle.command.CommandArgumentResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for resolving a specific type of argument
 *
 * @param <C> life cycle type
 * @param <R> the resolver type
 */
@NullMarked
public final class CommandArgumentResolverContainer<C, R> {

    private final Map<LifecycleAware<C>, CommandArgumentResolver<R>> contents;

    public CommandArgumentResolverContainer() {
        this.contents = new HashMap<>();
    }

    public boolean load(LifecycleAware<C> owner, CommandArgumentResolver<R> argumentResolver) {
        return contents.putIfAbsent(owner, argumentResolver) == null;
    }

    @Nullable
    public CommandArgumentResolver<R> query(LifecycleAware<C> preferred, @Nullable LifecycleAware<C> backup) {
        var resolver = this.contents.get(preferred);
        if (resolver != null) return resolver;
        if (backup != null) return this.contents.get(backup);
        return null;
    }
}
