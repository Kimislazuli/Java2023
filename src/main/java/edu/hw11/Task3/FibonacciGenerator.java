package edu.hw11.Task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public final class FibonacciGenerator {
    private static final String OWNER = "FibonacciCalculator";
    private static final String NAME = "fib";
    private static final String DESCRIPTOR = "(I)J";
    private static final int STACK_SIZE = 6;

    private FibonacciGenerator() {}

    public static class FibonacciByteCodeAppender implements ByteCodeAppender {

        @Override
        public Size apply(
            MethodVisitor methodVisitor,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            Label recLabel = new Label();

            methodVisitor.visitCode();

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LCONST_1);

            methodVisitor.visitInsn(Opcodes.LCMP);
            methodVisitor.visitJumpInsn(Opcodes.IFGT, recLabel);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            methodVisitor.visitLabel(recLabel);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitInsn(Opcodes.LSUB);
            methodVisitor.visitInsn(Opcodes.L2I);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC,
                OWNER,
                NAME,
                DESCRIPTOR,
                false);

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LSUB);
            methodVisitor.visitInsn(Opcodes.L2I);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC,
                OWNER,
                NAME,
                DESCRIPTOR,
                false);

            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            methodVisitor.visitEnd();

            return new ByteCodeAppender.Size(STACK_SIZE, methodDescription.getStackSize());
        }
    }
}
