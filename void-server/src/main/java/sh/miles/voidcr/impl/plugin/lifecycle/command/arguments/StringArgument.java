package sh.miles.voidcr.impl.plugin.lifecycle.command.arguments;

import sh.miles.voidcr.plugin.lifecycle.command.CommandArgumentResolver;

public class StringArgument implements CommandArgumentResolver<String> {
    @Override
    public String resolve(final String literal) throws IllegalStateException {
        return literal;
    }
}
