package com.comeup.design.visitor;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

/**
 * @auth: qwf
 * @date: 2024年1月8日 0008
 * @description:
 */
public class ASMVisitor {



    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassReader cr = new ClassReader(ASMVisitor.class.getClassLoader().getResourceAsStream("com/comeup/design/visitor/ASMTarget.class"));

        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM4, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                //return mv;
                return new MethodVisitor(ASM4, mv) {
                    @Override
                    public void visitCode() {
                        visitMethodInsn(INVOKESTATIC, "com/comeup/design/visitor/ASMProxy","before", "()V", false);
                        super.visitCode();
                    }
                };
            }
        };

        cr.accept(cv, 0);
        byte[] b2 = cw.toByteArray();

        MyClassLoader cl = new MyClassLoader();
        cl.loadClass("com.comeup.design.visitor.ASMProxy");
        Class c2 = cl.defineClass("com.comeup.design.visitor.ASMTarget", b2);
        c2.getConstructor().newInstance();


        String path = (String)System.getProperties().get("user.dir");
        File f = new File(path + "/com/comeup/design/visitor/");
        f.mkdirs();

        FileOutputStream fos = new FileOutputStream(path + "/com/comeup/design/visitor/ASMTarget_0.class");
        fos.write(b2);
        fos.flush();
        fos.close();

    }
}
