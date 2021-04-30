package com.chen.common.sccommoncore.jucdemo.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: scdemo
 * @description: Executors线程池，实战不建议使用该模式，应使用new ThreadPoolExecutor
 * @author: chenxiaoming
 * @create: 2021-04-30 11:33:22
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        // 单个线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 固定线程
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 可伸缩线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {

            for (int i = 1; i <= 10; i++) {
                final int temp = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "执行了" + temp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
