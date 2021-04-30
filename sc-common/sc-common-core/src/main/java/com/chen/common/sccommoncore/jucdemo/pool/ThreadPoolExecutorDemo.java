package com.chen.common.sccommoncore.jucdemo.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: scdemo
 * @description: 手动创建ThreadPoolExecutor线程池
 * new ThreadPoolExecutor.AbortPolicy() 当执行线程数大于线程池设置线程数时，不会处理多余的线程，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy() 当执行线程数大于线程池设置线程数时，多余线程会被主线程执行，不抛异常
 * new ThreadPoolExecutor.AbortPolicy() 当执行线程数大于线程池设置线程数时，多余的线程将会丢失，不抛异常
 * new ThreadPoolExecutor.AbortPolicy() 当执行线程数大于线程池设置线程数时，多余的线程会等待已完成工作的线程执行，如果没有线程则丢失，不抛异常
 * @author: chenxiaoming
 * @create: 2021-04-30 11:40:12
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                // 核心线程数
                2,
                // 最大线程数：cpu核数 or IO密集型
                5,
                // 超时时间
                3,
                // 时间单位
                TimeUnit.SECONDS,
                // 阻塞队列，规则：当执行的线程等于阻塞队列数+最大线程数：3+5 = 8时，会启动全部线程执行程序
                new ArrayBlockingQueue<>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 1; i <= 10; i++) {
                final int temp = i;
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "执行了" + temp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
