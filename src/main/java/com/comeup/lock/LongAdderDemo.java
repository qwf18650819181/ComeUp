package com.comeup.lock;

import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

/**
 * @auth: qwf
 * @date: 2024年1月2日 0002
 * @description:
 */
public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                longAdder.add(random.nextLong());
            }).start();
        }
    }
}
