package sh.miles.voidcr.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.net.URI

abstract class DownloadCosmicReachServer : DefaultTask() {

    @get:Input
    abstract var archiveRepoUrl: String

    @get:Input
    abstract var crPhase: String

    @get:Input
    abstract var CRVersion: String

    @get:OutputFile
    abstract val outputJar: RegularFileProperty

    @TaskAction
    fun execute() {
        val jarUrl = "$archiveRepoUrl$crPhase/$CRVersion/server/Cosmic-Reach-Server-$CRVersion.jar"
        val file = outputJar.get().asFile

        if (!file.exists()) {
            val url = URI(jarUrl).toURL()
            file.parentFile.mkdirs()
            url.openStream().use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }
    }
}
