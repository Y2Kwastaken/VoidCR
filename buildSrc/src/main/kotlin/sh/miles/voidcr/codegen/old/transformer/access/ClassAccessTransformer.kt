package sh.miles.voidcr.codegen.old.transformer.access

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class ClassAccessTransformer(
    private val fields: Map<String, AccessTransformation>,
    private val methods: Map<String, AccessTransformation>,
    visitor: ClassVisitor
) : ClassVisitor(Opcodes.ASM9, visitor) {

    override fun visitField(
        access: Int, name: String, descriptor: String?, signature: String?, value: Any?
    ): FieldVisitor {
        Opcodes.ACC_STATIC
        var accessType = access
        if (fields.containsKey(name)) {
            val result = fields[name]!!
            accessType = if ((access and Opcodes.ACC_STATIC) != 0) result.access + Opcodes.ACC_STATIC else result.access
        }

        return super.visitField(accessType, name, descriptor, signature, value)
    }

    override fun visitMethod(
        access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?
    ): MethodVisitor {
        var accessType = access
        if (methods.containsKey(name)) {
            val result = methods[name]!!
            accessType = if ((access and Opcodes.ACC_STATIC) != 0) result.access + Opcodes.ACC_STATIC else result.access
        }

        return super.visitMethod(accessType, name, descriptor, signature, exceptions)
    }
}
