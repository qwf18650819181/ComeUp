package com.comeup.design.observer.demo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @auth: qwf
 * @date: 2024年2月17日 0017
 * @description:
 */
@Slf4j
public class MyObserverTwo implements IMyObserver<MyEventTwo> {
    public void publishEvent(MyEventTwo mySubject)
    {
        log.info("MyObserverTwo receive message: " + JSON.toJSONString(mySubject));
    }
}
