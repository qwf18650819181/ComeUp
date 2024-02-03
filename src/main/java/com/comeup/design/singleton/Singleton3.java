package com.comeup.design.singleton;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class Singleton3 {

    private static Singleton3 singleton;

    private Singleton3() {
    }
    public static Singleton3 getInstance() {
        if (singleton == null) {
            singleton = new Singleton3();
        }
        return singleton;
    }
}
