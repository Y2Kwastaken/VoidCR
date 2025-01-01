package sh.miles.voidcr.task

import org.apache.commons.compress.archivers.zip.ZipFile
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.UntrackedTask
import java.nio.file.OpenOption
import java.nio.file.StandardOpenOption
import kotlin.io.path.notExists
import kotlin.io.path.outputStream

@UntrackedTask(because = "Always must be applied after patches")
abstract class SetupSourcesTask : DefaultTask() {
//    @get:InputFile // eventually this might be used
//    abstract val decompiledJar: RegularFileProperty

    @get:InputFile
    abstract val patchedJar: RegularFileProperty

//    @get:InputFile // eventually this might be used
//    abstract val failedPatchJar: RegularFileProperty

    @get:OutputDirectory
    abstract val sourceDir: DirectoryProperty

    @TaskAction
    fun execute() {
        val sources = sourceDir.asFile.get().toPath()
        val patchedJar = patchedJar.asFile.get()

        if (patchedJar.exists()) {
            ZipFile(patchedJar).use { zipFile ->
                zipFile.entries.asSequence().forEach { zipEntry ->
                    zipFile.getInputStream(zipEntry).use { input ->
                        val file = sources.resolve(zipEntry.name)
                        if (file.parent.notExists()) file.parent.toFile().mkdirs()
                        file.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }
                }
            }
        }
    }
}
