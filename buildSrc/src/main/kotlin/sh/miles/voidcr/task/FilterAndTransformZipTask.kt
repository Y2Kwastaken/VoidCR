package sh.miles.voidcr.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import sh.miles.voidcr.codegen.Codegen
import sh.miles.voidcr.codegen.old.transformer.access.TransformationData
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

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

        val transData = TransformationData.read(atFile.get().asFile.toPath())
        val inputZip = ZipFile(input)
        ZipOutputStream(output.outputStream()).use { writer ->
            for (entry in inputZip.entries()) {
                if (!filterFunc(entry.name)) continue
                val bytes = inputZip.getInputStream(entry).readAllBytes()
                if (transData.has(entry.name)) {
                    writer.putNextEntry(ZipEntry(entry.name))
                    writer.write(Codegen.applyAccessTransformations(transData, bytes))
                } else {
                    writer.putNextEntry(entry)
                    writer.write(bytes)
                }

                writer.closeEntry()
            }
        }
    }

}
