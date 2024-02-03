package com.comeup.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
public class JavaMain {


    public static class Person extends Observable {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
            setChanged();
            notifyObservers(message);
        }
    }

    public static class Police implements Observer {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Police(String name) {
            this.name = name;
        }

        @Override
        public void update(Observable o, Object arg) {
            System.out.println("Police " + name + " received message: " + arg);
        }
    }

    public static void main(String[] args) {
        Person subject = new Person();

        // Add observers
        subject.addObserver(new Police("A"));
        subject.addObserver(new Police("B"));

        // Change message
        subject.setMessage("Hello, World!");
    }




}
