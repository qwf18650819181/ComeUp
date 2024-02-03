package com.comeup.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @auth: qwf
 * @date: 2024年1月2日 0002
 * @description:
 */
public class CyclicBarrierDemo {
    private static final int THREAD_COUNT = 3;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT, () -> {
            System.out.println("All threads have reached the barrier. Continuing execution.");
        });

        for (int i = 0; i < THREAD_COUNT; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(finalI + "begin");
                    cyclicBarrier.await();
                    System.out.println(finalI + "end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        }


    }
}
