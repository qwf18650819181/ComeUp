package com.comeup.multithread;

import cn.hutool.core.date.StopWatch;
import co.paralleluniverse.fibers.Fiber;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description: 纤程
 */
public class QuasarDemo {


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Fiber> fibers = new ArrayList<>();

        for (int i = 0; i < 10000_00L; i++) {
            Fiber<Void> fiber = new Fiber<>(() -> System.out.println("1234"));
            fiber.start();
            fibers.add(fiber);
        }
        fibers.forEach(item -> {
            try {
                item.join();
            } catch (Exception ignored) {
            }
        });
        System.out.println(System.currentTimeMillis() - start + "end");
    }
}
