package sh.miles.voidcr.codegen

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import sh.miles.voidcr.codegen.old.transformer.access.ClassAccessTransformer
import sh.miles.voidcr.codegen.old.transformer.access.TransformationData

object Codegen {

    fun applyAccessTransformations(transformation: TransformationData, bytes: ByteArray): ByteArray {
        val reader = ClassReader(bytes)
        val writer = ClassWriter(reader, 0)
        val transform = ClassAccessTransformer(transformation.fields, transformation.methods, writer)
        reader.accept(transform, 0)
        return writer.toByteArray()
    }

}
