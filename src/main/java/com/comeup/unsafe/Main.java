//package com.comeup.unsafe;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
///**
// * @auth: qwf
// * @date: 2023年12月29日 0029
// * @description:
// */
//public class Main {
//
//
//    static int a = 0;
//    static int counter = 0;
//    static Unsafe unsafe;
//    static {
//
//        try {
//            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//            theUnsafe.setAccessible(true);
//            unsafe = (Unsafe) theUnsafe.get(null);
//        } catch (Exception e) {
//
//        }
//    }
//
//
//
//    /**
//     * case 1
//     *          opts
//     *              --add-modules=jdk.unsupported --add-opens=java.base/sun.nio.ch=ALL-UNNAMED
//     *          java complie +
//     *              --add-modules=jdk.unsupported
//     * @param args
//     * @throws NoSuchFieldException
//     * @throws IllegalAccessException
//     */
//    //java -Xbootclasspath:D:/myprogram/ComeUp/build/classes/java/main/com/comeup/unsafe Main
//    // export JAVA_OPTS="-Xbootclasspath:D:/myprogram/ComeUp/src/main/java/com/comeup/unsafe"
//    // JCTools
//    // jdk1.8 静态属性保存在class对象后面,也即堆内存中class对象的后面,所以需要取class类的偏向
//    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
//        // 字段是静态的，因此可以使用null作为get()方法的参数，表示在静态上下文中获取该字段的值
//        // 这段代码的目的通常是通过获取Unsafe对象来进行一些底层的操作，因为Unsafe类提供了一些直接操作内存和执行特权操作的方法，但它通常被认为是不安全的，因此只能在受信任的环境中使用
//
//        // case 1
////        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
////        theUnsafe.setAccessible(true);
////        Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
//
//        // case 2 failed
////        Unsafe UNSAFE = Unsafe.getUnsafe();
//
//        // case 3
////        Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
////        Field theUnsafeField = unsafeClass.getDeclaredField("theUnsafe");
////        theUnsafeField.setAccessible(true);
////        Unsafe UNSAFE = (Unsafe) theUnsafeField.get(null);
//
//        // 编译时添加参数
//        //--add-exports java.base/jdk.internal.misc=ALL-UNNAMED
//        //运行时添加参数
//        //--add-opens java.base/jdk.internal.misc=ALL-UNNAMED success
//
//
//
//
//        Class<Main> mainClass = Main.class;
//        long l = unsafe.staticFieldOffset(mainClass.getDeclaredField("a"));
//
//        unsafe.compareAndSwapInt(Main.class, l, a, ++a);
//
//        System.out.println(a);
//
//        lock();
//        a++;
//        unlock();
//
//        System.out.println(a);
//
//    }
//
//    public static void lock() throws NoSuchFieldException {
//        for (;;) {
//            int t = counter;
//            long l = unsafe.staticFieldOffset(Main.class.getDeclaredField("counter"));
//            if (unsafe.compareAndSwapInt(Main.class, l, 0 , 1)) {
//                break;
//            }
//        }
//    }
//    public static void unlock() {
//        counter = 0;
//    }
//
//
//}
