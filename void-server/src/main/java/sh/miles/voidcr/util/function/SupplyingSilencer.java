package sh.miles.voidcr.util.function;

public interface SupplyingSilencer<R> {

    R uncheck() throws Throwable;

    static <R> R silence(SupplyingSilencer<R> silencer) {
        try {
            return silencer.uncheck();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
