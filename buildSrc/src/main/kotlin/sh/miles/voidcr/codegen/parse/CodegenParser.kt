package sh.miles.voidcr.codegen.parse

import org.objectweb.asm.Opcodes.ACC_PRIVATE
import org.objectweb.asm.Opcodes.ACC_PROTECTED
import org.objectweb.asm.Opcodes.ACC_PUBLIC
import sh.miles.voidcr.codegen.data.AccessTransformation
import sh.miles.voidcr.codegen.data.CodegenOutput
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.nio.file.Path
import kotlin.io.path.inputStream

object CodegenParser {

    private const val COMMENT = '#'
    private const val START_TAG = '<'
    private const val END_TAG = '>'
    private const val NEWLINE = '\n'

    private const val NEWLINE_CODE = NEWLINE.code
    private const val SPACE_CODE = ' '.code
    private const val PARENTHESES_CODE = '('.code

    fun parse(file: Path): CodegenOutput {
        val transformations = mutableListOf<AccessTransformation>()
        BufferedReader(InputStreamReader(file.inputStream())).use { reader ->
            var char: Char
            var open = false
            var operation: CodegenOperation? = null

            while (reader.read().also { char = it.toChar() } != -1) {
                if (char == COMMENT) {
                    skipTillSig(reader, NEWLINE_CODE)
                    continue
                }

                if (char == START_TAG) {
                    if (open) {
                        throw IllegalArgumentException("Illegal open brace inside already open brace")
                    }

                    open = true
                    operation = CodegenOperation.fromId(
                        collectTillSig(
                            reader,
                            NEWLINE_CODE,
                            { it.toChar() },
                            ::StringBuilder,
                            StringBuilder::append
                        ).toString()
                    )
                    continue
                }

                if (char == END_TAG) {
                    throw IllegalArgumentException("Operation parsers should handle closing tags")
                }

                when (operation) {
                    CodegenOperation.ACCESS_TRANSFORMER -> {
                        transformations.addAll(parseAccessTransformations(reader))
                    }

                    else -> throw IllegalArgumentException("Invalid codegen operation $operation")
                }
            }
        }

        return CodegenOutput(transformations)
    }

    private fun parseAccessTransformations(reader: Reader): List<AccessTransformation> {
        val collector = mutableListOf<AccessTransformation>()

        var char: Char
        var section = 0
        var access = 0
        while (reader.read().also { char = it.toChar() } != -1) {
            if (char == END_TAG) {
                skipTillSig(reader, NEWLINE_CODE)
                return collector
            }

            if (char == COMMENT) {
                skipTillSig(reader, NEWLINE_CODE)
                continue
            }

            if (char == NEWLINE) {
                section = 0
                access = 0
                continue
            }

            when (section) {
                0 -> {
                    if (char == 'p') {
                        val next = reader.read()
                        if (next == -1) throw IllegalArgumentException("Invalid file termination while reading access transformation")
                        when (next.toChar()) {
                            'u' -> {
                                access += ACC_PUBLIC
                            }

                            'r' -> {
                                val further = reader.read()
                                if (further == -1) throw IllegalArgumentException("Invalid file termination while reading access transformation")

                                access += when (further.toChar()) {
                                    'i' -> ACC_PRIVATE
                                    'o' -> ACC_PROTECTED
                                    else -> throw IllegalArgumentException("Unknown access while reading termination on sequence '$char$next$further'")
                                }
                            }
                        }
                        skipTillSig(reader, SPACE_CODE)
                        section++
                    }
                }

                1 -> {
                    val path = collectTillAnySig(
                        reader,
                        setOf(NEWLINE_CODE, PARENTHESES_CODE),
                        { it.toChar() },
                        ::StringBuilder,
                        StringBuilder::append,
                        true
                    ).insert(0, char)

                    val descriptor = if (path.endsWith('(')) {
                        collectTillSig(
                            reader,
                            NEWLINE_CODE,
                            { it.toChar() },
                            ::StringBuilder,
                            StringBuilder::append
                        ).toString()
                    } else {
                        null
                    }

                    path.setLength(path.length - 1)
                    collector.add(
                        AccessTransformation(
                            access,
                            JvmClasspath(
                                if (descriptor == null) JvmClasspath.FIELD else JvmClasspath.METHOD,
                                path.toString(),
                                "($descriptor"
                            )
                        )
                    )
                }

                else -> {
                    throw IllegalArgumentException("Section '$section' is out of bounds")
                }
            }
        }

        throw IllegalArgumentException("AT Tag did not end with terminating tag '$END_TAG'")
    }

}

enum class CodegenOperation(val id: String) {
    ACCESS_TRANSFORMER("AT"),
    API_CONVERTER("AC");

    companion object {
        @Throws(IllegalArgumentException::class)
        fun fromId(id: String): CodegenOperation {
            return when (id) {
                "AT" -> ACCESS_TRANSFORMER
                "AC" -> API_CONVERTER
                else -> throw IllegalArgumentException("Invalid Codegen operation id $id valid ids [AT, AC] you provided $id")
            }
        }
    }
}

fun main() {
    CodegenParser.parse(Path.of("void-server/data/voidcr.codegen"))
}
