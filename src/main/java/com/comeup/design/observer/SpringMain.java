package com.comeup.design.observer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
public class SpringMain {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        // Publish event
        context.publishEvent(new MyEvent(new Object(), "Hello, World!"));
        context.publishEvent(new MyAutoEvent(new Object(), "Hello, auto!"));

        context.close();
    }
}


@Configuration
@ComponentScan("com.comeup.design.observer")
class MyConfiguration {
}

// Event
class MyEvent extends ApplicationEvent {
    private final String message;

    public MyEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

// Event
class MyAutoEvent extends ApplicationEvent {
    private final String message;

    public MyAutoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

// Listener
@Component
class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("Received: " + event.getMessage());
    }
}

// Listener
@Component
class MyAutoEventListener {

    @EventListener
    public void onApplicationEvent(MyAutoEvent event) {
        System.out.println("Auto Received: " + event.getMessage());
    }
    @EventListener
    public void onApplicationEvent(MyEvent event) {
        System.out.println("Auto Received: " + event.getMessage());
    }
}