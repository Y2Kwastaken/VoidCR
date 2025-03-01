package sh.miles.voidcr.plugin.lifecycle.command;

import sh.miles.voidcr.chat.Messageable;
import sh.miles.voidcr.util.annotations.SupportsExtending;

/**
 * An implementable interface that manages command execution logic
 *
 * @since 0.3.27
 */
@SupportsExtending
public interface CommandExecutor {
    void execute(Messageable executor, CommandContext context);
}
