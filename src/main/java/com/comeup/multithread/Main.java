package com.comeup.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @auth: qwf
 * @date: 2023年12月22日 0022
 * @description:
 */
public class Main implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Main main = new Main();
        FutureTask<String> futureTask = new FutureTask<>(main);
        Thread thread = new Thread(futureTask);
        thread.start();
        String s = futureTask.get();

        System.out.println(1);
        System.out.println(s);


    }

    @Override
    public String call() throws Exception {
        Thread.sleep(4000);
        return "hello world";
    }
}
