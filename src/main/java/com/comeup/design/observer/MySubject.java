package com.comeup.design.observer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
public class MySubject {

    private List<MyObserver> observers = new ArrayList<>();

    public MySubject(String message) {
        this.message = message;
        this.observers.add(new MyRealObserver1());
        this.observers.add(new MyRealObserver2());
    }

    @Getter
    private String message;

    public void publishSubject(String message) {
        this.message = message;
        observers.forEach(observer -> observer.publishSubject(this));
    }

    public static void main(String[] args) {
        MySubject helloJack = new MySubject("hello jack");

        helloJack.publishSubject("hello key");


    }
}
