package com.chen.common.sccommoncore.jucdemo.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: scdemo
 * @description: 同步队列SynchronousQueue使用
 * @author: chenxiaoming
 * @create: 2021-04-30 10:32:18
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue synchronousQueue = new SynchronousQueue();

        new Thread(() -> {
            try {
                System.out.println("写入a");
                synchronousQueue.put("a");
                System.out.println("写入b");
                synchronousQueue.put("b");
                System.out.println("写入c");
                synchronousQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                System.out.println("读取：" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("读取：" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("读取：" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
