//package com.comeup.unsafe;
//
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.VarHandle;
//
///**
// * @auth: qwf
// * @date: 2023年12月29日 0029
// * @description:
// */
//public class VarHandleMain {
//    private int value;
//
//    private static final VarHandle VALUE_HANDLE;
//
//    static {
//        try {
//            VALUE_HANDLE = MethodHandles.lookup().findVarHandle(VarHandleMain.class, "value", int.class);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            throw new Error(e);
//        }
//    }
//
//    public void increment() {
//        int oldValue;
//        int newValue;
//        do {
//            oldValue = (int) VALUE_HANDLE.getVolatile(this);
//            newValue = oldValue + 1;
//        } while (!VALUE_HANDLE.compareAndSet(this, oldValue, newValue));
//    }
//
//    public int getValue() {
//        return (int) VALUE_HANDLE.get(this);
//    }
//
//    public static void main(String[] args) {
//        VarHandleMain varHandleMain = new VarHandleMain();
//        int value1 = varHandleMain.getValue();
//        System.out.println(value1);
//        varHandleMain.increment();
//        int value2 = varHandleMain.getValue();
//        System.out.println(value2);
//    }
//
//}
