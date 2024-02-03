package com.comeup.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @auth: qwf
 * @date: 2024年1月2日 0002
 * @description:
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(countDownLatch.getCount());
            }).start();
        }

        countDownLatch.await();
        System.out.println("success");

    }
}
