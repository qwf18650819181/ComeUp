package com.comeup.design.observer.demo;

import lombok.Data;

/**
 * @auth: qwf
 * @date: 2024年2月17日 0017
 * @description:
 */
@Data
public class MyEventOne implements IMyEvent {
    private String message;
}
