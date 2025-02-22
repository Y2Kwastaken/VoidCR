package sh.miles.voidcr.task

import io.codechicken.diffpatch.match.FuzzyLineMatcher
import io.codechicken.diffpatch.util.PatchMode

abstract class ApplyPatchesTask: AbstractApplyPatchesTask() {
    override val patchMode: PatchMode = PatchMode.OFFSET
    override val fuzz: Float = FuzzyLineMatcher.DEFAULT_MIN_MATCH_SCORE
}
