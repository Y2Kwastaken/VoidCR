package sh.miles.voidcr.codegen.transformer

data class AccessTransformation private constructor (val access: Int, val fileName: String, val name: String, val type: Byte) {
    companion object {
        private const val FIELD: Byte = 0;
        private const val METHOD: Byte = 1;

        fun field(access: Int, className: String, name: String): AccessTransformation {
            return AccessTransformation(access, className, name, FIELD);
        }

        fun method(access: Int, className: String, name: String): AccessTransformation {
            return AccessTransformation(access, className, name, METHOD)
        }
    }
}
