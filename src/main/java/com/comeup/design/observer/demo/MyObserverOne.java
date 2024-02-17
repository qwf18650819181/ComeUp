package com.comeup.design.observer.demo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @auth: qwf
 * @date: 2024年2月17日 0017
 * @description:
 */
@Slf4j
public class MyObserverOne implements IMyObserver<MyEventOne> {
    public void publishEvent(MyEventOne mySubject)
    {
        log.info("MyObserverOne receive message: " + JSON.toJSONString(mySubject));
    }
}
