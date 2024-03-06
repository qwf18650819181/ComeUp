package com.comeup.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @auth: qwf
 * @date: 2024年1月5日 0005
 * @description:
 */
public class JDKProxy implements IJDKProxy {

    @Override
    public void move() {
        System.out.println("kakakaka");
    }



    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IJDKProxy jdkProxy = (IJDKProxy) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), new Class[]{IJDKProxy.class}, new MyInvocationHandler(new JDKProxy()));
        jdkProxy.move();

    }

    public static class MyInvocationHandler implements InvocationHandler {
        JDKProxy jdkProxy;

        public MyInvocationHandler(JDKProxy jdkProxy) {
            this.jdkProxy = jdkProxy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before.....");
            Object invoke = method.invoke(jdkProxy, args);
            System.out.println("after.....");
            return invoke;
        }
    }
}
