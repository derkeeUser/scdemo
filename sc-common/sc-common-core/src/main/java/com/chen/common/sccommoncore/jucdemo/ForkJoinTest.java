package com.chen.common.sccommoncore.jucdemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @program: scdemo
 * @description: ForkJoin使用：大数据量使用
 * @author: chenxiaoming
 * @create: 2021-04-30 16:08:04
 */
public class ForkJoinTest {

    private final static Long START = 0L;
    private final static Long END = 100_0000_0000L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 初
        test1();
        // 中
        test2();
        // 高
        test3();
    }

    public static void test1() {
        long startTime = System.currentTimeMillis();
        long total = 0;
        // 小数据量计算
        for (long i = START; i <= END; i++) {
            total += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + "结果：" + total);
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(START, END);
        ForkJoinTask<Long> submit = pool.submit(task);
        Long total = submit.get();
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + "结果：" + total);
    }

    public static void test3() {
        long startTime = System.currentTimeMillis();
        long total = LongStream.rangeClosed(START, END).parallel().reduce(0, Long::sum);
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + "结果：" + total);
    }
}

class ForkJoinDemo extends RecursiveTask<Long> {

    private final Long start;
    private final Long end;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected Long compute() {
        long total = 0L;
        long middle = 10000L;
        if ((end - start) <= middle) {
            // 小数据量计算
            for (long i = start; i <= end; i++) {
                total += i;
            }
        } else {
            // 大数据量计算
            // 中间值：用于将forkJoin分流操作
            long temp = (end + start) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, temp);
            task1.fork();
            total += task1.join();
            // 拆分任务计算
            ForkJoinDemo task2 = new ForkJoinDemo(temp + 1, end);
            task2.fork();
            total += task2.join();
        }
        return total;
    }
}