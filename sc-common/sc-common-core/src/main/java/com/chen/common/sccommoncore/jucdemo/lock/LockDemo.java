package com.chen.common.sccommoncore.jucdemo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: scdemo
 * @description: ReentrantLock锁
 * @author: chenxiaoming
 * @create: 2021-04-29 10:55:40
 */
public class LockDemo {
    public static void main(String[] args) {
        ReentrantLockBiz sect = new ReentrantLockBiz();
        new Thread(() -> {for (int i = 0; i < 100; i++) {sect.decrement();}},"A").start();
        new Thread(() -> {for (int i = 0; i < 100; i++) {sect.decrement();}},"B").start();
    }
}

class ReentrantLockBiz {
    Lock lock = new ReentrantLock();

    private int nums = 100;

    public void decrement() {
        lock.lock();
        try {
            if (nums > 0) {
                System.out.println(Thread.currentThread().getName() + "成功卖出1，剩余：" + (--nums));
            }
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
