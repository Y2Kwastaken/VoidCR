package sh.miles.voidcr.artisan

import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.ACC_PRIVATE
import org.objectweb.asm.Opcodes.ACC_PUBLIC
import org.objectweb.asm.Opcodes.ACC_TRANSIENT
import org.objectweb.asm.Opcodes.ACC_TRANSITIVE
import org.objectweb.asm.Opcodes.ALOAD
import org.objectweb.asm.Opcodes.ARETURN
import org.objectweb.asm.Opcodes.ASM9
import org.objectweb.asm.Opcodes.DUP
import org.objectweb.asm.Opcodes.DUP_X1
import org.objectweb.asm.Opcodes.F_SAME
import org.objectweb.asm.Opcodes.F_SAME1
import org.objectweb.asm.Opcodes.GETFIELD
import org.objectweb.asm.Opcodes.GOTO
import org.objectweb.asm.Opcodes.IFNONNULL
import org.objectweb.asm.Opcodes.IFNULL
import org.objectweb.asm.Opcodes.INVOKESPECIAL
import org.objectweb.asm.Opcodes.NEW
import org.objectweb.asm.Opcodes.PUTFIELD
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldInsnNode
import org.objectweb.asm.tree.FieldNode
import org.objectweb.asm.tree.FrameNode
import org.objectweb.asm.tree.InsnList
import org.objectweb.asm.tree.InsnNode
import org.objectweb.asm.tree.JumpInsnNode
import org.objectweb.asm.tree.LabelNode
import org.objectweb.asm.tree.LocalVariableNode
import org.objectweb.asm.tree.MethodInsnNode
import org.objectweb.asm.tree.MethodNode
import org.objectweb.asm.tree.TypeInsnNode
import org.objectweb.asm.tree.VarInsnNode
import sh.miles.artisan.asm.ArtisanAccessUtil
import sh.miles.artisan.extension.ArtisanExtension
import sh.miles.artisan.extension.ContainerHandler
import sh.miles.artisan.util.JvmClasspath
import sh.miles.artisan.util.log.ArtisanLogger
import sh.miles.artisan.visitor.LiteralResult
import kotlin.math.log

class APIMethodExtension : ArtisanExtension {

    override fun name(): String {
        return "VoidCR API Method Generator"
    }

    override fun version(): String {
        return "1.0.0"
    }

    override fun buildHandlers(): MutableList<ContainerHandler> {
        return mutableListOf(ApiMethodContainerHandler())
    }
}

class ApiMethodContainerHandler : ContainerHandler {
    private val extendAccess = mutableMapOf<JvmClasspath, Int>()
    private val generations = mutableMapOf<JvmClasspath, JvmClasspath>()

    override fun parse(literal: LiteralResult, logger: ArtisanLogger) {
        val pair = literal.literal.split(" ").map { JvmClasspath(JvmClasspath.CLASS, it, null, null) }
        generations[pair[1]] = pair[0]
        val expandAccess = literal.getMetaValue("DefineAccess")
        if (expandAccess != null) extendAccess[pair[0]] =
            expandAccess.split(",").sumOf { ArtisanAccessUtil.scopeToOpcode(it) }
        logger.info("Found Api Generation for class ${pair[0].path} in ${pair[1].path}")
    }

    override fun visit(node: ClassNode, path: JvmClasspath, logger: ArtisanLogger) {
        val generation = this.generations[path]!!
        val expandAccess = this.extendAccess.getOrDefault(generation, ACC_PRIVATE)
        node.fields.add(
            FieldNode(ASM9, expandAccess + ACC_TRANSIENT, "cache", "L${generation.path};", null, null)
        )


        val instructions = InsnList()
        val getterMethod = MethodNode(ACC_PUBLIC, "getVoidMirror", "()L${generation.path};", null, null)
        val L0 = LabelNode()
        val L1 = LabelNode()
        val L2 = LabelNode()
        val L3 = LabelNode()
        instructions.add(L0)
        instructions.add(VarInsnNode(ALOAD, 0))
        instructions.add(FieldInsnNode(GETFIELD, path.path, "cache", "L${generation.path};"))
        instructions.add(JumpInsnNode(IFNONNULL, L1))
        instructions.add(VarInsnNode(ALOAD, 0))
        instructions.add(TypeInsnNode(NEW, generation.path))
        instructions.add(InsnNode(DUP))
        instructions.add(VarInsnNode(ALOAD, 0))
        instructions.add(MethodInsnNode(INVOKESPECIAL, generation.path, "<init>", "(L${path.path};)V", false))
        instructions.add(InsnNode(DUP_X1))
        instructions.add(FieldInsnNode(PUTFIELD, path.path, "cache", "L${generation.path};"))
        instructions.add(JumpInsnNode(GOTO, L2))
        instructions.add(L1)
        instructions.add(VarInsnNode(ALOAD, 0))
        instructions.add(FieldInsnNode(GETFIELD, path.path, "cache", "L${generation.path};"))
        instructions.add(L2)
        instructions.add(InsnNode(ARETURN))
        instructions.add(L3)
        getterMethod.localVariables.add(LocalVariableNode("this", "L${path.path};", null, L0, L3, 0));
        getterMethod.instructions.add(instructions)
        getterMethod.maxStack = 3
        getterMethod.maxLocals = 1

        node.methods.add(getterMethod)
    }

    override fun doesModify(path: JvmClasspath): Boolean {
        return generations.containsKey(path)
    }

    override fun containerName(): String {
        return "APIGEN"
    }

}
