package com.comeup.design.observer;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
public class MyRealObserver1 implements MyObserver {

    @Override
    public void publishSubject(MySubject mySubject) {
        System.out.println("MyRealObserver1 receive " + mySubject.getMessage());
    }
}
