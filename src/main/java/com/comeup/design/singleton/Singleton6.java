package com.comeup.design.singleton;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class Singleton6 {


    private Singleton6() {
    }

    private static class Singleton6Holder {
        public static final Singleton6 singleton6 = new Singleton6();
    }

    public static Singleton6 getInstance() {
        return Singleton6Holder.singleton6;
    }
}
