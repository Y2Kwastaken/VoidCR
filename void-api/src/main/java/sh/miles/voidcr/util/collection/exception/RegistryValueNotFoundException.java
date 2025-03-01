package sh.miles.voidcr.util.collection.exception;

public class RegistryValueNotFoundException extends RuntimeException {

    public RegistryValueNotFoundException(Object key) {
        super("No value exists for the key " + key);
    }
}
