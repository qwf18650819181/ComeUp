package com.comeup.classsize;

import com.javaup.CustomClassAgent;
import org.openjdk.jol.info.ClassLayout;

/**
 * @auth: qwf
 * @date: 2023年12月21日 0021
 * @description:
 */
public class Main {

    /**
     * 获得对象大小
     * 配置 -javaagent:libs/JavaUp-1.0-SNAPSHOT.jar
     */
    public static long getSize() {
        Object o = new Object();
        long l = CustomClassAgent.sizeOf(o);
        System.out.println(l);
        return l;
    }

    /**
     * -XX:+PrintFlagsFinal | grep 'Biase*'
     */
    public static void main(String[] args) {
        getSize();
    }


}
