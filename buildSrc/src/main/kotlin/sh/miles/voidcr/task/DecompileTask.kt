package sh.miles.voidcr.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler

abstract class DecompileTask : DefaultTask() {

    @get:InputFile
    abstract val decompileTarget: RegularFileProperty

    @get:OutputFile
    abstract val decompilerOutput: RegularFileProperty

    @get:Input
    abstract val decompilerArguments: ListProperty<String>

    init {
        decompilerArguments.convention(mutableListOf())
    }

    @TaskAction
    fun execute() {
        val jarFile = decompileTarget.asFile.get()
        val outputFile = decompilerOutput.asFile.get()

        if (!outputFile.parentFile.exists()) {
            outputFile.parentFile.mkdirs()
        }

        if (outputFile.exists()) {
            outputFile.delete()
        }

        val arguments = decompilerArguments.get().toMutableList()
        arguments.addLast(jarFile.toString())
        arguments.addLast(outputFile.toString())
        ConsoleDecompiler.main(arguments.toTypedArray())
    }
}
