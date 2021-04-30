package com.chen.common.sccommoncore.jucdemo;

import java.util.concurrent.CountDownLatch;

/**
 * @program: scdemo
 * @description: 倒计时CountDownLatch使用
 * @author: chenxiaoming
 * @create: 2021-04-29 16:33:45
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                System.out.println("学生" + Thread.currentThread().getName() + "出去教室");
                // 倒数器-1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("所有学生都已出去教室，老师关闭教师门");
    }
}
