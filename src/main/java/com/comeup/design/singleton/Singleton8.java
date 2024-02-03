package com.comeup.design.singleton;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class Singleton8 {
    private static Singleton8 singleton;

    private Singleton8() {
    }

    public static Singleton8 getInstance() {
        if (singleton == null) {
            synchronized (Singleton8.class) {
                singleton = new Singleton8();
            }
        }
        return singleton;
    }
}
