package com.comeup.design.singleton;

/**
 * @auth: qwf
 * @date: 
 * @description: 饿汉静态代码块初始化单例
 */
public class Singleton2 {

    private static Singleton2 singleton1;
    static {
        singleton1 = new Singleton2();
    }

    private Singleton2() {
    }
    public static Singleton2 getInstance() {
        return singleton1;
    }
}
