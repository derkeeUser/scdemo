package com.chen.common.sccommoncore.jucdemo.sup;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @program: scdemo
 * @description: Semaphore信号量使用
 * @author: chenxiaoming
 * @create: 2021-04-29 16:33:45
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    // 获取，假设已经满了，等待被释放
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放，会将当前的信号量释放+1，唤醒等待的线程
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
