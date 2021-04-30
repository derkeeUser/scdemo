package com.chen.common.sccommoncore.jucdemo;

import java.util.concurrent.TimeUnit;

/**
 * @program: scdemo
 * @description: synchronized锁的是调用方，同一对象调用方法时需排队等待
 * @author: chenxiaoming
 * @create: 2021-04-29 11:28:30
 */
public class SynchronizedCallerDemo {
    public static void main(String[] args) {
        SynchronizedCallerBiz synchronizedCallerBiz = new SynchronizedCallerBiz();
        SynchronizedCallerBiz sect66 = new SynchronizedCallerBiz();

        new Thread(() -> {
            synchronizedCallerBiz.printA();
        },"A").start();

        new Thread(() -> {
            sect66.printB();
        },"B").start();
    }
}

class SynchronizedCallerBiz {

    /**
     * synchronized锁的是调用方
     */
    public synchronized void printA() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("AAAAAAAAAAAAA");
    }
    public synchronized void printB() {
        System.out.println("BBBBBBBBBBBBB");
    }
}