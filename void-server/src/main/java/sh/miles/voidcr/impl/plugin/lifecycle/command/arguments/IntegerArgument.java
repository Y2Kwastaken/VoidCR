package sh.miles.voidcr.impl.plugin.lifecycle.command.arguments;

import sh.miles.voidcr.plugin.lifecycle.command.CommandArgumentResolver;

public class IntegerArgument implements CommandArgumentResolver<Integer> {
    @Override
    public Integer resolve(final String literal) throws IllegalStateException {
        try {
            return Integer.parseInt(literal);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Unable to parse integer from string input %s".formatted(literal));
        }
    }
}
