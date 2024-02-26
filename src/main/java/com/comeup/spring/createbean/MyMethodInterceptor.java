package com.comeup.spring.createbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @auth: qwf
 * @date: 2024年2月26日 0026
 * @description:
 */
@Slf4j
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("MyMethodInterceptor intercept");
        return methodProxy.invokeSuper(o, objects);
    }
}
