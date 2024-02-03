package com.comeup.design.singleton;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class Singleton4 {

    private static Singleton4 singleton;

    private Singleton4() {
    }
    public static synchronized Singleton4 getInstance() {
        if (singleton == null) {
            singleton = new Singleton4();
        }
        return singleton;
    }
}
