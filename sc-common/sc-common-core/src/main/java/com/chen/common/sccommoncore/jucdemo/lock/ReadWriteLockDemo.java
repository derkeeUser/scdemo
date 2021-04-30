package com.chen.common.sccommoncore.jucdemo.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: scdemo
 * @description: 读写锁
 * 独占锁(写锁)：一次只能被一个线程占有
 * 共享锁(读锁)：可以多线程同时占有
 * @author: chenxiaoming
 * @create: 2021-04-29 18:02:47
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(String.valueOf(temp), temp);
            }, String.valueOf(i)).start();
            new Thread(() -> {
                System.out.println(myCache.get(String.valueOf(temp)));;
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache<T> {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Map<String, T> cacheMap = new HashMap<>();

    public void put(String key, T value) {
        try {
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "写入：[" + key + "," + value + "]");
            cacheMap.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入成功：[" + key + "," + value + "]");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public T get(String key) {
        T value = null;
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "读取：" + key);
            value = cacheMap.get(key);
            System.out.println(Thread.currentThread().getName() + "读取成功：" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }
}