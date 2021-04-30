package com.chen.common.sccommoncore.jucdemo.lock;

/**
 * @program: scdemo
 * @description: synchronized锁
 * @author: chenxiaoming
 * @create: 2021-04-29 10:55:40
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        SynchronizedBiz aSynchronizedBiz = new SynchronizedBiz();
        new Thread(() -> {for (int i = 0; i < 20; i++) {
            aSynchronizedBiz.decrement();}},"A").start();
        new Thread(() -> {for (int i = 0; i < 20; i++) {
            aSynchronizedBiz.decrement();}},"B").start();
    }
}

class SynchronizedBiz {

    private int nums = 20;

    public synchronized void decrement() {
        if (nums > 0) {
            System.out.println(Thread.currentThread().getName() + "成功卖出1，剩余：" + (--nums));
        }
    }
}
