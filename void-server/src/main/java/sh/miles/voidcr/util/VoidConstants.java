package sh.miles.voidcr.util;

import java.nio.file.Path;
import java.util.function.Supplier;

public final class VoidConstants {

    public static final Path ORBIT_CONFIG = Path.of("orbit.json");
    public static final Path PLUGINS_PATH = Path.of("plugins");

    private VoidConstants() {
        throw new UnsupportedOperationException("Can not initialize utility class");
    }

    public static <T> T getOrConstant(Supplier<T> supplier, T constant) {
        T temp = supplier.get();
        if (temp == null) {
            return constant;
        }
        return temp;
    }

}
