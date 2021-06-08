package com.chen.common.sccommoncore.jucdemo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: scdemo
 * @description: 可重入锁的使用：获取外部对象的锁后，相当于获取内部所有的锁
 * @author: chenxiaoming
 * @create: 2021-05-05 15:00:25
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone2 phone2 = new Phone2();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();

        new Thread(() -> {
            phone2.sms();
        }, "C").start();

        new Thread(() -> {
            phone2.sms();
        }, "D").start();
    }
}

/**
 * synchronized可重入锁demo
 */
class Phone {

    public synchronized void sms() {
        System.out.println("=============== synchronized可重入锁demo ================");
        System.out.println(Thread.currentThread().getName() + " send messaging...");
        call();
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " calling...");
    }
}

/**
 * ReentrantLock可重入锁demo：lock unlock必须成对出现，否则会造成死锁
 */
class Phone2 {
    Lock lock = new ReentrantLock();

    public void sms() {
        lock.lock();
        try {
            System.out.println("=============== ReentrantLock可重入锁demo ================");
            System.out.println(Thread.currentThread().getName() + " send messaging...");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " calling...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}