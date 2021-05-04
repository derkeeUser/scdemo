package com.chen.common.sccommoncore.jucdemo.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: scdemo
 * @description: 使用原子类保证volatile的原子性
 * @author: chenxiaoming
 * @create: 2021-05-04 14:06:30
 */
public class AtomicDemoTest {
    public static void main(String[] args) {
        //new Counter对象
        Counter counter = new Counter();
        AtomicCounter atomicCounter = new AtomicCounter();
        //循环建立20个线程
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                //循环1000次agePlus方法
                for (int j = 0; j < 1000; j++) {
                    counter.agePlus();
                    atomicCounter.agePlus();
                }
            },String.valueOf(i)).start();
        }
        //判断20个线程分别执行1000次自增方法是否结束
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("current age is:"+counter.age);
        System.out.println("current age is:"+atomicCounter.num);
    }
}

class Counter{
    /**
     * 此处用volatile修饰后，不能保证原子性，程序运行后，得不到最终预期的结果
     */
    volatile int age = 0;

    /**
     * 使用synchronized后可保证原子性
     */
    public synchronized void agePlus(){
        age ++;
    }
}

class AtomicCounter{
    /**
     * 用juc中的原子类可解决上述问题
     */
    volatile AtomicInteger num = new AtomicInteger();

    public void agePlus(){
        num.getAndIncrement();
    }
}