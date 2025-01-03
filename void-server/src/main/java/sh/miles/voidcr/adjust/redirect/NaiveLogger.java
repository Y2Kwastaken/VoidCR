package sh.miles.voidcr.adjust.redirect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class NaiveLogger {

    public static final Logger LOGGER = LogManager.getLogger("finalforeach.cosmicreach");

    private NaiveLogger() {
        throw new UnsupportedOperationException("The class %s can not be initialized".formatted(getClass()));
    }

    public static void info(Object message) {
        LOGGER.info(message);
    }

    public static void warn(Object message) {
        LOGGER.warn(message);
    }

    public static void error(Object message) {
        LOGGER.error(message);
    }

    public static void debug(Object message) {
        LOGGER.error(message);
    }
}
