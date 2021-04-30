package com.chen.common.sccommoncore.jucdemo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: scdemo
 * @description: callable的使用
 * @author: chenxiaoming
 * @create: 2021-04-29 15:24:26
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);

        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        Object obj = futureTask.get();
        System.out.println(obj.toString());
    }
}

class MyThread implements Callable<String> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        System.out.println("1024");
        return "hello world!!!";
    }
}