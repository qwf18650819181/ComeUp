package com.comeup.design.singleton;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class Singleton5 {

    private static Singleton5 singleton;

    private Singleton5() {
    }
    public static Singleton5 getInstance() {
        if (singleton == null) {
            synchronized(Singleton5.class) {
                if (singleton == null) {
                    singleton = new Singleton5();
                }
            }
        }
        return singleton;
    }
}
