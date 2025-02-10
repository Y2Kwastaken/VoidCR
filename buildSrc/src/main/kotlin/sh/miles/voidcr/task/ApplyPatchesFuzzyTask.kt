package sh.miles.voidcr.task

import io.codechicken.diffpatch.match.FuzzyLineMatcher
import io.codechicken.diffpatch.util.PatchMode

abstract class ApplyPatchesFuzzyTask : AbstractApplyPatchesTask() {
    override val patchMode: PatchMode = PatchMode.FUZZY
    override val fuzz: Float = FuzzyLineMatcher.DEFAULT_MIN_MATCH_SCORE
}
