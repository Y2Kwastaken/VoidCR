package sh.miles.voidcr

import org.slf4j.Logger
import sh.miles.artisan.util.log.ArtisanLogger

class GradleArtisanLogger(private val logger: Logger) : ArtisanLogger {

    override fun info(string: String) {
        this.logger.info(string)
    }

    override fun warn(string: String) {
        this.logger.warn(string)
    }

    override fun debug(string: String) {
        this.logger.debug(string)
    }

    override fun error(string: String) {
        this.logger.error(string)
    }

    override fun throwing(string: String, exception: Throwable) {
        this.logger.trace(string, exception)
    }
}
