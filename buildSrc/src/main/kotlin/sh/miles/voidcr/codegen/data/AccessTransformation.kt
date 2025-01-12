package sh.miles.voidcr.codegen.data

import org.objectweb.asm.Opcodes.*
import sh.miles.voidcr.codegen.parse.JvmClasspath

data class AccessTransformation(val access: Int, val path: JvmClasspath) {

    fun mergeAccess(merge: Int): Int {
        if (!validateMerge(
                merge, this.access
            )
        ) {
            throw IllegalArgumentException("Can not merge access when expansion shrunk scope or invalid modifiers are used")
        }

        var build = 0

        if (access and ACC_STATIC != 0) { // always append static if present
            build += ACC_STATIC
        }

        if (access and ACC_FINAL != 0) { // always append final if present
            build += ACC_FINAL
        }

        return build + access // merge rest
    }

    private fun validateMerge(old: Int, new: Int): Boolean {
        return !(old and ACC_PUBLIC != 0 && new and ACC_PRIVATE == 0 && new and ACC_PROTECTED == 0) &&
                (new and ACC_STATIC == 0) && (new and ACC_PRIVATE == 0) && (new and ACC_FINAL == 0)
    }
}
