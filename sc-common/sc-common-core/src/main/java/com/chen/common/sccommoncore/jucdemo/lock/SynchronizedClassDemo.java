package com.chen.common.sccommoncore.jucdemo.lock;

import java.util.concurrent.TimeUnit;

/**
 * @program: scdemo
 * @description: 当用static修饰时，synchronized锁的Class，不同对象调用方法时需排队等待
 * @author: chenxiaoming
 * @create: 2021-04-29 11:28:30
 */
public class SynchronizedClassDemo {
    public static void main(String[] args) {
        SynchronizedClassBiz synchronizedClassBiz = new SynchronizedClassBiz();
        SynchronizedClassBiz sect77 = new SynchronizedClassBiz();

        new Thread(() -> {
            synchronizedClassBiz.printA();
        },"A").start();

        new Thread(() -> {
            sect77.printB();
        },"B").start();
    }
}

class SynchronizedClassBiz {

    /**
     * static修饰下，synchronized锁的是Class
     */
    public static synchronized void printA() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("AAAAAAAAAAAAA");
    }
    public static synchronized void printB() {
        System.out.println("BBBBBBBBBBBBB");
    }
}