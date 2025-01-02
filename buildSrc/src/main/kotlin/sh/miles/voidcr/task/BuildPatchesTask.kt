package sh.miles.voidcr.task

import io.codechicken.diffpatch.cli.DiffOperation
import io.codechicken.diffpatch.util.Input.MultiInput
import io.codechicken.diffpatch.util.LogLevel
import io.codechicken.diffpatch.util.Output.MultiOutput
import io.codechicken.diffpatch.util.archiver.ArchiveFormat
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.UntrackedTask

@UntrackedTask(because = "Always should rebuild patches do not cache")
abstract class BuildPatchesTask : DefaultTask() {

    @get:InputFile
    abstract val decompiledJar: RegularFileProperty

    @get:InputDirectory
    abstract val sourceDir: DirectoryProperty

    @get:OutputDirectory
    abstract val patchDir: DirectoryProperty

    @TaskAction
    fun execute() {
        val decompile = decompiledJar.asFile.get().toPath()
        val source = sourceDir.asFile.get().toPath()
        val patches = patchDir.asFile.get().toPath()

        DiffOperation.builder()
            .logTo { logger.lifecycle(it) }
            .baseInput(MultiInput.archive(ArchiveFormat.ZIP, decompile))
            .changedInput(MultiInput.folder(source))
            .patchesOutput(MultiOutput.folder(patches))
            .autoHeader(true)
            .level(LogLevel.ALL)
            .lineEnding("\n")
            .context(3)
            .summary(true)
            .build()
            .operate()
    }
}
