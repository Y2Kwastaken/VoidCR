package sh.miles.voidcr.codegen.parse

data class JvmClasspath(val type: Byte, val path: String, val descriptor: String?) {
    companion object {
        const val FIELD: Byte = 0;
        const val METHOD: Byte = 1;
        const val CLASS: Byte = 2;
    }

    fun isType(type: Byte): Boolean {
        return this.type == type;
    }

}
