package sh.miles.voidcr.util;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.function.Supplier;

public final class VoidConstants {

    public static final Path JAR_DIRECTORY;

    static {
        try {
            JAR_DIRECTORY = Path.of(VoidConstants.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

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
