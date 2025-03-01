package sh.miles.voidcr.impl.plugin.lifecycle.command.arguments;

import sh.miles.voidcr.plugin.lifecycle.command.CommandArgumentResolver;

public class DoubleArgument implements CommandArgumentResolver<Double> {

    @Override
    public Double resolve(final String literal) throws IllegalStateException {
        try {
            return Double.parseDouble(literal);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Unable to parse double from string input %s".formatted(literal));
        }
    }
}
