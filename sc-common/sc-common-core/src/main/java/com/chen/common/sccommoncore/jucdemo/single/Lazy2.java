package com.chen.common.sccommoncore.jucdemo.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: scdemo
 * @description: 懒汉模式
 * @author: chenxiaoming
 * @create: 2021-05-04 14:51:44
 */
public class Lazy2 {

    /**
     * volatile保证可见性、指令重排
     */
    private static volatile Lazy2 lazy;

    private Lazy2() {
        // 多线程环境下保障正常
        synchronized (Lazy2.class) {
            // 通过Lazy获取单例后，防止后续通过反射破坏
            if (lazy != null) {
                throw new RuntimeException("禁止使用反射破坏单例");
            }
        }
    }

    public static Lazy2 getInstance() {
        if (lazy == null) {
            // 在多线程环境下,不使用双重校验锁的单例会遭到破坏(会产生多个对象)
            synchronized (Lazy2.class) {
                if (lazy == null) {
                    lazy = new Lazy2();
                }
            }
        }
        return lazy;
    }

    public static void main(String[] args) {
        // 反射破坏双重校验锁
        // 通过Lazy获取单例后，防止后续通过反射破坏
        // 此方式不能完全抵御反射破坏，如果不使用单例类创建对象则不能抵御反射破坏
        try {
            Lazy2 instance = Lazy2.getInstance();
            Constructor<Lazy2> constructor = Lazy2.class.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            Lazy2 instance2 = constructor.newInstance();

            System.out.println(instance);
            System.out.println(instance2);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
