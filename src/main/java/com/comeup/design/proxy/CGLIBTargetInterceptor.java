package com.comeup.design.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @auth: qwf
 * @date: 2024年1月5日 0005
 * @description:
 */
public class CGLIBTargetInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before invoke " + method + " " + proxy);
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After invoke " + method);
        return result;
    }
    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGLIBTargetObject.class);
        enhancer.setCallback(new CGLIBTargetInterceptor());
        CGLIBTargetObject proxyObject = (CGLIBTargetObject) enhancer.create();
        proxyObject.move();
    }
    public static class CGLIBTargetObject {
        public void move() {
            System.out.println("kakakaka");
        }
    }
}

