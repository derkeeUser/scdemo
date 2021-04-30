package com.chen.common.sccommoncore.jucdemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: scdemo
 * @description: 计数器CyclicBarrier使用
 * @author: chenxiaoming
 * @create: 2021-04-29 16:33:45
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        // 当cyclicBarrier到10后会执行指定线程System.out.println("所有学生都已出去教室，老师关闭教师门");可循环
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,()->{System.out.println("所有学生都已出去教室，老师关闭教师门");});
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                System.out.println("学生" + Thread.currentThread().getName() + "出去教室");
                try {
                    // 计数器+1
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
