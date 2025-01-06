package sh.miles.voidcr.util;

public final class ReflectionUtils {

    public static void setStaticField(String name, Class<?> clazz, final Object value) {
        try {
            final var field = clazz.getDeclaredField(name);
            if (field.trySetAccessible()) {
                field.set(null, value);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
