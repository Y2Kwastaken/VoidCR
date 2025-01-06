package sh.miles.voidcr.server.registry.exception;

import sh.miles.voidcr.util.NamedKey;

public class RegistryValueNotFoundException extends RuntimeException {

    public RegistryValueNotFoundException(NamedKey key) {
        super("No value exists for the NamedKey " + key);
    }
}
