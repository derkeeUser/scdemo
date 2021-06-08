package com.chen.common.sccommoncore.jucdemo.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: scdemo
 * @description: 自旋锁的使用
 * @author: chenxiaoming
 * @create: 2021-05-05 15:12:26
 */
public class SpinLockTest {

    private static int num;

    public static void main(String[] args) {
        // 测试自定义锁的使用：验证是否能保证 A num=100  B num=200
        SpinLockDemo lock = new SpinLockDemo();

        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 100; i++) {
                    num++;
                }
                System.out.println(Thread.currentThread().getName() + " num=" + num);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "A").start();


        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 100; i++) {
                    num++;
                }
                System.out.println(Thread.currentThread().getName() + " num=" + num);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "B").start();
    }
}

/**
 * 自定义锁：底层基于自旋锁
 */
class SpinLockDemo {

    AtomicReference<Thread> reference = new AtomicReference();

    public void lock() {
        Thread thread = Thread.currentThread();
        while (!reference.compareAndSet(null, thread)) {
            // 如果cas失败就自旋
        }
        System.out.println(Thread.currentThread().getName() + " get lock");
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + " release the lock");
    }
}
