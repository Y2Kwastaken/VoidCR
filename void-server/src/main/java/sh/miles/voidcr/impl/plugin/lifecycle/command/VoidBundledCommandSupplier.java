package sh.miles.voidcr.impl.plugin.lifecycle.command;

import finalforeach.cosmicreach.chat.commands.Command;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class VoidBundledCommandSupplier<C> implements Supplier<Command> {

    private final VoidCommandContextHolder<C> holder;
    private final Function<VoidCommandContextHolder<C>, Command> creator;
    private Command cache;

    public VoidBundledCommandSupplier(final VoidCommandContextHolder<C> holder, final Function<VoidCommandContextHolder<C>, Command> creator) {
        this.holder = holder;
        this.creator = creator;
    }

    @Override
    public Command get() {
        if (this.cache == null) {
            this.cache = this.creator.apply(this.holder);
        }
        return this.cache;
    }

    public VoidCommandContextHolder<C> getContext() {
        return this.holder;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final VoidBundledCommandSupplier<?> that)) return false;
        return Objects.equals(holder, that.holder) && Objects.equals(creator, that.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holder, creator);
    }
}
