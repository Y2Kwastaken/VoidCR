package sh.miles.voidcr.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import sh.miles.artisan.ArtisanExtensions
import sh.miles.artisan.ArtisanFormat
import sh.miles.voidcr.GradleArtisanLogger
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.inputStream

abstract class FilterAndTransformZipTask : DefaultTask() {

    @get:Input
    abstract val filterFunction: Property<(String) -> Boolean>

    @get:InputFile
    abstract val inputJar: RegularFileProperty

    @get:InputFile
    abstract val atFile: RegularFileProperty

    @get:OutputFile
    abstract val outputJar: RegularFileProperty

    init {
        filterFunction.convention { true }
    }

    @TaskAction
    fun execute() {
        val filterFunc = filterFunction.get()

        val input = inputJar.get().asFile
        val output = outputJar.get().asFile

        val reader = ArtisanFormat.asReader(atFile.get().asFile.toPath().inputStream())
        val classEditor = ArtisanExtensions.newDefaultEditor()
            .logger(GradleArtisanLogger(project.logger))
            .syntaxTreeReader(reader)
        val inputZip = ZipFile(input)
        ZipOutputStream(output.outputStream()).use { writer ->
            for (entry in inputZip.entries()) {
                if (!filterFunc(entry.name)) continue
                val bytes = inputZip.getInputStream(entry).readAllBytes()
                writer.putNextEntry(ZipEntry(entry.name))
                writer.write(
                    classEditor.clearClassProvider()
                        .classBytes(bytes)
                        .run()
                )
                writer.closeEntry()
            }
        }
    }

}
