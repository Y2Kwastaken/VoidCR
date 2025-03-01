package sh.miles.voidcr.impl.plugin.lifecycle.command.arguments;

import sh.miles.voidcr.plugin.lifecycle.command.CommandArgumentResolver;

public class FloatArgument implements CommandArgumentResolver<Float> {

    @Override
    public Float resolve(final String literal) throws IllegalStateException {
        try {
            return Float.parseFloat(literal);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Unable to parse float from string input %s".formatted(literal));
        }
    }
}
