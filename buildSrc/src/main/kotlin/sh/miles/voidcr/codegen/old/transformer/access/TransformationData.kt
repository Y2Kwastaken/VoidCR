package sh.miles.voidcr.codegen.old.transformer.access

import org.objectweb.asm.Opcodes
import java.io.File
import java.nio.file.Path
import kotlin.io.path.readLines

data class TransformationData(
    val fields: Map<String, AccessTransformation>,
    val methods: Map<String, AccessTransformation>,
    val keys: Set<String>
) {

    fun has(fileName: String): Boolean {
        val fixedFileName = fileName.replace(File.separator, ".").replace(".class", "");
        return keys.contains(fixedFileName)
    }

    companion object {
        fun read(file: Path): TransformationData {
            val fields = mutableMapOf<String, AccessTransformation>()
            val methods = mutableMapOf<String, AccessTransformation>()
            val keys = mutableSetOf<String>()

            for (line in file.readLines()) {
                var section = 0
                var access = 0
                val className = StringBuilder()
                val targetName = StringBuilder()
                var method = false

                var character: Char
                var index = -1
                while (index != line.length - 1) {
                    index++
                    character = line[index]
                    if (character == ' ') {
                        section++
                        continue
                    }

                    when (section) {
                        0 -> {
                            when (character) {
                                'r' -> {} // r in pr
                                'p' -> {
                                    access += if (line[index + 1] == 'r') {
                                        Opcodes.ACC_PRIVATE
                                        index++
                                    } else Opcodes.ACC_PUBLIC
                                }

                                'f' -> access += Opcodes.ACC_FINAL
                                else -> throw IllegalStateException("Invalid opcode operation $character")
                            }
                        }

                        1 -> {
                            className.append(character)
                        }

                        2 -> {
                            if (character == '(' && line[index + 1] == ')') {
                                method = true
                                index++
                            } else targetName.append(character)
                        }

                        else -> {
                            throw IllegalStateException("Invalid Access Transformation notation for line $line")
                        }
                    }
                }

                val cn = className.toString()
                val n = targetName.toString()
                if (method) {
                    methods[n] = AccessTransformation.method(access, cn, n)
                } else {
                    fields[n] = AccessTransformation.field(access, cn, n)
                }
                keys.add(cn)
            }

            return TransformationData(fields.toMap(), methods.toMap(), keys.toSet())
        }
    }
}
