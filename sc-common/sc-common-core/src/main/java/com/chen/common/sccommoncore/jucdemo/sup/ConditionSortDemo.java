package com.chen.common.sccommoncore.jucdemo.sup;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: scdemo
 * @description: Condition  await()/signal() 排序打印
 * @author: chenxiaoming
 * @create: 2021-04-29 11:28:30
 */
public class ConditionSortDemo {
    public static void main(String[] args) {
        ConditionSortBiz conditionSortBiz = new ConditionSortBiz();
        new Thread(() -> { for (int i = 0; i < 10; i++) {
            conditionSortBiz.printA(); } }, "A").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) {
            conditionSortBiz.printB(); } }, "B").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) {
            conditionSortBiz.printC(); } }, "C").start();
    }
}

class ConditionSortBiz {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    private int num = 1;

    public void printA() {
        lock.lock();
        try {
            while (num != 1) {
                // 等待
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "线程,num = " + num);
            num=2;
            condition2.signal();
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (num != 2) {
                // 等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "线程,num = " + num);
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (num != 3) {
                // 等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "线程,num = " + num);
            num = 1;
            condition.signal();
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}