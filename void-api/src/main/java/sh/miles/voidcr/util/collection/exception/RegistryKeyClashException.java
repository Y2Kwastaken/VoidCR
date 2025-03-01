package sh.miles.voidcr.util.collection.exception;

public class RegistryKeyClashException extends RuntimeException {

    public RegistryKeyClashException(Object clash) {
        super("Value of keys are clashing within registry " + clash);
    }
}
