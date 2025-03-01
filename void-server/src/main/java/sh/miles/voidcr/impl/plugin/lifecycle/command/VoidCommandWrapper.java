package sh.miles.voidcr.impl.plugin.lifecycle.command;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.chat.IChat;
import finalforeach.cosmicreach.chat.commands.Command;
import org.jspecify.annotations.Nullable;
import sh.miles.voidcr.impl.entity.VoidPlayerEntity;
import sh.miles.voidcr.plugin.lifecycle.command.CommandArgumentResolver;
import sh.miles.voidcr.plugin.lifecycle.command.CommandContext;

public class VoidCommandWrapper<C> extends Command implements CommandContext {

    private final VoidCommandContextHolder<C> context;

    public VoidCommandWrapper(VoidCommandContextHolder<C> context) {
        this.context = context;
    }

    @Override
    public void run(final IChat chat) {
        context.getExecutor().execute((VoidPlayerEntity) getCallingPlayer().getEntity().getVoidMirror(), this);
    }

    @Override
    public String getShortDescription() {
        return this.context.getDescription();
    }

    @Override
    public <R> R getArgumentOrElse(final int index, final Class<R> type, @Nullable final R fallback) throws IndexOutOfBoundsException, IllegalArgumentException {
        try {
            return getArgument(index, index, type);
        } catch (IndexOutOfBoundsException | IllegalStateException exception) {
            return fallback;
        }
    }

    @Override
    public <R> R getArgument(final int index, final Class<R> type) throws IndexOutOfBoundsException, IllegalArgumentException, IllegalStateException {
        return getArgument(index, index, type);
    }

    @Override
    public <R> R getArgument(final int startIndex, final int endIndex, final Class<R> type) throws IndexOutOfBoundsException, IllegalArgumentException, IllegalStateException {
        Preconditions.checkPositionIndex(startIndex, super.args.length - 1, "Given startIndex is out of bound when attempting to get command argument");
        Preconditions.checkPositionIndex(endIndex, super.args.length - 1, "Given endIndex is out of bound when attempting to get command argument");

        final StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i++) {
            builder.append(super.args[i + 1]);
        }

        final String argument = builder.toString();
        final CommandArgumentResolver<R> resolver = this.context.getLifecycle().resolveArgument(type, this.context.getPreferredSource(type), this.context.getOwner());
        return resolver.resolve(argument);
    }

    @Override
    public String getArgument(final int index) throws IndexOutOfBoundsException {
        Preconditions.checkPositionIndex(index, super.args.length - 1, "Given index is out of bound when attempting to get command argument");
        return super.args[index + 1];
    }

    @Override
    public int getArgumentCount() {
        return super.args.length - 1;
    }
}
