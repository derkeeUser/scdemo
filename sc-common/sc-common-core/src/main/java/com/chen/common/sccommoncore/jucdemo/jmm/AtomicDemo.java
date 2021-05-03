package com.chen.common.sccommoncore.jucdemo.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: scdemo
 * @description: volatile不保证原子性
 * @author: chenxiaoming
 * @create: 2021-05-03 17:58:27
 */
public class AtomicDemo {

    /**
     * 此处用volatile修饰后，不能保证原子性，程序运行后，得不到最终预期的结果
     */
    private volatile static int num = 0;

    /**
     * 用juc中的原子类可解决上述问题
     */
    private static volatile AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                add();
                add2();
            }).start();
        }

        // java默认有两个线程：gc和main
        if (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "num " + num);
        System.out.println(Thread.currentThread().getName() + "atomicInteger " + atomicInteger);
    }

    public static void add() {
        num++;
    }

    public static void add2() {
        atomicInteger.getAndIncrement();
    }
}
