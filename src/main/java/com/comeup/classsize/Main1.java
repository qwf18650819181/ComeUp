package com.comeup.classsize;

import org.openjdk.jol.info.ClassLayout;

/**
 * @auth: qwf
 * @date: 2023年12月21日 0021
 * @description:
 */
public class Main1 {
    /**
     * -XX:+PrintFlagsFinal | grep 'Biase*'
     * -XX:+UseBiasedLocking
     * 无锁 -> 匿名偏向锁 -> 偏向锁 -> 自旋锁 -> 重量级锁
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        Object obj = new Object();
        // 打印对象布局
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            synchronized (obj) {
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
        }


    }
}
