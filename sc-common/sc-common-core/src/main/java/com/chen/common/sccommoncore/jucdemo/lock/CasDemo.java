package com.chen.common.sccommoncore.jucdemo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: scdemo
 * @description: CAS使用
 * @author: chenxiaoming
 * @create: 2021-05-05 14:33:36
 */
public class CasDemo {
    public static void main(String[] args) {
        System.out.println("=====================test1=====================");
        test1();
        System.out.println("=====================test2=====================");
        test2();
    }

    public static void test1() {
        AtomicInteger num = new AtomicInteger(1);

        System.out.println(num.get());
        // 此处为cas操作，原理：如果比较的值与期望的值相同，就执行更新操作
        num.compareAndSet(1, 2);
        System.out.println(num.get());
    }

    public static void test2() {
        // 原子引用，可根据stamp进行类似乐观锁的操作
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(1, 1);

        // 模拟ABA问题
        // 当线程A将 a=1 改成 a=2，而线程B将 a=1 改成a=6时，A又将改成 a=1，此时就是一个ABA问题
        new Thread(() -> {
            int stamp = reference.getStamp();
            System.out.println(Thread.currentThread().getName() + "1 stamp =>" + stamp);

            System.out.println(Thread.currentThread().getName() + "1 =>" + reference.compareAndSet(1, 2, reference.getStamp(), reference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "1 =>" + reference.getReference());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "2 =>" + reference.compareAndSet(2, 1, reference.getStamp(), reference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "2 =>" + reference.getReference());
        }, "A").start();


        new Thread(() -> {
            int stamp = reference.getStamp();
            System.out.println(Thread.currentThread().getName() + "1 stamp =>" + stamp);

            System.out.println(Thread.currentThread().getName() + "1 =>" + reference.compareAndSet(1, 6, reference.getStamp(), reference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "1 =>" + reference.getReference());
        }, "B").start();

    }
}
