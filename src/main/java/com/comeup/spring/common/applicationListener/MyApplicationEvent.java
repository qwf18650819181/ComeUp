package com.comeup.spring.common.applicationListener;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @auth: qwf
 * @date: 2024年1月24日 0024
 * @description:
 */
public class MyApplicationEvent extends ApplicationEvent {

    public MyApplicationEvent(Object source) {
        super(source);
    }

    public MyApplicationEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
