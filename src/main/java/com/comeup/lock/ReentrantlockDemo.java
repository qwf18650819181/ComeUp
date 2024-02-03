package com.comeup.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auth: qwf
 * @date: 2024年1月2日 0002
 * @description:
 */
public class ReentrantlockDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        condition1.notify();
        try {

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
    public static void main1(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        try {

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
