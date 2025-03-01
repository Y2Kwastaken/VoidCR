package sh.miles.voidcr.impl.plugin.lifecycle.command.arguments;

import sh.miles.voidcr.plugin.lifecycle.command.CommandArgumentResolver;

public class LongArgument implements CommandArgumentResolver<Long> {
    @Override
    public Long resolve(final String literal) throws IllegalStateException {
        try {
            return Long.parseLong(literal);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Unable to parse long from string input %s".formatted(literal));
        }
    }
}
