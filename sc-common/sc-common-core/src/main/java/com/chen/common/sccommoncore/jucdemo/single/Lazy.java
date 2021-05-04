package com.chen.common.sccommoncore.jucdemo.single;

/**
 * @program: scdemo
 * @description: 懒汉模式
 * @author: chenxiaoming
 * @create: 2021-05-04 14:51:44
 */
public class Lazy {

    /**
     * volatile保证可见性、指令重排
     */
    private volatile static Lazy lazy;

    private Lazy() {
        System.out.println(Thread.currentThread().getName()+" ok");
    }

    public static Lazy getInstance() {
        if (lazy == null) {
            // 在多线程环境下,不使用双重校验锁的单例会遭到破坏(会产生多个对象)
            lazy = new Lazy();
        }
        return lazy;
    }

    public static Lazy getInstance2() {
        if (lazy == null) {
            // 双重校验锁
            synchronized (Lazy.class) {
                if (lazy == null) {
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }

    public static void main(String[] args) {
        // 多线程模拟测试两种懒汉模式
        // 此方式不能抵御反射破坏
        for (int i = 0; i < 10; i++) {
            //new Thread(() -> {
            //    System.out.println(Thread.currentThread().getName()+"----"+Lazy.getInstance());
            //}, "A" + i).start();
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"----"+Lazy.getInstance2());
            }, "B" + i).start();
        }
    }
}
