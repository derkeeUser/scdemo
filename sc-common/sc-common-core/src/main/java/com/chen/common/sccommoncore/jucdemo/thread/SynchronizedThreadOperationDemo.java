package com.chen.common.sccommoncore.jucdemo.thread;

/**
 * @program: scdemo
 * @description: synchronized  wait()/notify()/notifyAll()
 * @author: chenxiaoming
 * @create: 2021-04-29 11:28:30
 */
public class SynchronizedThreadOperationDemo {
    public static void main(String[] args) {
        SynchronizedThreadOperationBiz synchronizedThreadOperationBiz = new SynchronizedThreadOperationBiz();
        new Thread(() -> {for (int i = 0; i < 10; i++) {
            try {
                synchronizedThreadOperationBiz.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"A").start();
        new Thread(() -> {for (int i = 0; i < 10; i++) {
            try {
                synchronizedThreadOperationBiz.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"B").start();
    }
}

class SynchronizedThreadOperationBiz {

    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        if (num == 0) {
            // 等待
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "线程,num = " + num);
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        if (num != 0) {
            // 等待
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "线程,num = " + num);
        this.notifyAll();
    }
}