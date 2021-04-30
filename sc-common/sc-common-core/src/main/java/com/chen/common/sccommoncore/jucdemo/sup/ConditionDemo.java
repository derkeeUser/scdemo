package com.chen.common.sccommoncore.jucdemo.sup;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: scdemo
 * @description: Condition  await()/signalAll()
 * @author: chenxiaoming
 * @create: 2021-04-29 11:28:30
 */
public class ConditionDemo {
    public static void main(String[] args) {
        ConditionBiz conditionBiz = new ConditionBiz();
        new Thread(() -> { for (int i = 0; i < 10; i++) {
            conditionBiz.increment(); } }, "A").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) {
            conditionBiz.decrement(); } }, "B").start();
    }
}

class ConditionBiz {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private int num = 0;

    public void increment() {
        lock.lock();
        try {
            while (num == 0) {
                // 等待
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "线程,num = " + num);
            condition.signalAll();
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (num != 0) {
                // 等待
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "线程,num = " + num);
            condition.signalAll();
        } catch (Exception e) {
            System.out.println("发生异常：" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}