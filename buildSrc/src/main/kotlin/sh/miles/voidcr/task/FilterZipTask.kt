package sh.miles.voidcr.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

abstract class FilterZipTask : DefaultTask() {

    @get:Input
    abstract val filterFunction: Property<(String) -> Boolean>

    @get:InputFile
    abstract val inputJar: RegularFileProperty

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

        ZipOutputStream(FileOutputStream(output)).use { outputStream ->
            ZipInputStream(FileInputStream(input)).use { stream ->
                var entry = stream.nextEntry
                while (entry != null) {
                    if (filterFunc(entry.name)) {
                        outputStream.putNextEntry(entry)
                        stream.copyTo(outputStream)
                        outputStream.closeEntry()
                    }

                    entry = stream.nextEntry
                }
            }
        }
    }

}
