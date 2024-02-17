package com.comeup.design.observer.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @auth: qwf
 * @date: 2024年2月17日 0017
 * @description:
 */
@Slf4j
public class EventTrigger {

    private static final MyObserverOne myObserverOne = new MyObserverOne();
    private static final MyObserverTwo myObserverTwo = new MyObserverTwo();


    public static void main(String[] args) {

        MyEventOne myEventOne = new MyEventOne();
        myEventOne.setMessage("你好信息1");
        MyEventTwo myEventTwo = new MyEventTwo();
        myEventTwo.setMessage("信息2");
        myEventTwo.setEventName("事件2");
        myObserverOne.publishEvent(myEventOne);
        myObserverTwo.publishEvent(myEventTwo);
    }


}
