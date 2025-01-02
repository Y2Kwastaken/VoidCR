package sh.miles.voidcr.task

import io.codechicken.diffpatch.cli.PatchOperation
import io.codechicken.diffpatch.match.FuzzyLineMatcher
import io.codechicken.diffpatch.util.Input.MultiInput
import io.codechicken.diffpatch.util.LogLevel
import io.codechicken.diffpatch.util.Output.MultiOutput
import io.codechicken.diffpatch.util.PatchMode
import io.codechicken.diffpatch.util.archiver.ArchiveFormat
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.UntrackedTask
import java.util.zip.ZipOutputStream
import kotlin.io.path.copyTo
import kotlin.io.path.exists
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.outputStream

@UntrackedTask(because = "Patches should not be cached")
abstract class ApplyPatchesTask : DefaultTask() {

    @get:InputDirectory
    abstract val patchDir: DirectoryProperty

    @get:InputFile
    abstract val inputFile: RegularFileProperty

    @get:OutputFile
    abstract val outputJar: RegularFileProperty

    @get:OutputFile
    abstract val failedPatchesJar: RegularFileProperty

    @TaskAction
    fun execute() {
        val hasPatches = patchDir.isPresent && run {
            val patches = patchDir.asFile.get().toPath()
            patches.exists() && patches.listDirectoryEntries().isNotEmpty()
        }

        val input = inputFile.asFile.get().toPath()
        val output = outputJar.asFile.get().toPath()
        val failed = failedPatchesJar.asFile.get().toPath()

        if (!hasPatches) {
            input.copyTo(output)
            ZipOutputStream(failed.outputStream().buffered())
            return
        }

        val result = PatchOperation.builder()
            .logTo { logger.lifecycle(it) }
            .baseInput(MultiInput.archive(ArchiveFormat.ZIP, input))
            .patchedOutput(MultiOutput.archive(ArchiveFormat.ZIP, output.outputStream()))
            .patchesInput(MultiInput.folder(patchDir.asFile.get().toPath()))
            .rejectsOutput(MultiOutput.archive(ArchiveFormat.ZIP, output.outputStream()))
            .level(LogLevel.ALL)
            .mode(PatchMode.OFFSET)
            .minFuzz(FuzzyLineMatcher.DEFAULT_MIN_MATCH_SCORE)
            .summary(true)
            .build()
            .operate()

        if (result.exit != 0) {
            throw Exception("Failed to apply ${result.summary?.failedMatches} patches")
        }
    }

}
