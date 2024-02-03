package com.comeup.synchronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auth: qwf
 * @date: 2023年12月20日 0020
 * @description:
 */
public class Main {

//    private volatile static int num;
    private static int num;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        Lock lock = new ReentrantLock();

        for (int i = 0; i< 10; i++) {
            new Thread(() -> {
                for (int k = 0; k< 100000; k++) {
                    while (true) {
                        if (lock.tryLock()) {
                            try {
                                num++;
                            } finally {
                                lock.unlock();
                                break;
                            }
                        }
                    }
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(num);
    }
}
