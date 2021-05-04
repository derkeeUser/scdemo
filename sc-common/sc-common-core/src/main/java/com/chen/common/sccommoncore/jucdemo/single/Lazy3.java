package com.chen.common.sccommoncore.jucdemo.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: scdemo
 * @description: 懒汉模式
 * @author: chenxiaoming
 * @create: 2021-05-04 14:51:44
 */
public class Lazy3 {

    private static boolean flag = false;
    /**
     * volatile保证可见性、指令重排
     */
    private static volatile Lazy3 lazy;

    private Lazy3() {
        // 多线程环境下保障正常
        synchronized (Lazy3.class) {
            // 如果对象不是通过单例对象获取，而是直接通过反射获取，则只允许第一次反射破坏
            // 此方式不能完全抵御反射破坏，如果反射后将flag值更改，则还是会被反射破坏
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("禁止使用反射破坏单例");
            }
        }
    }

    public static Lazy3 getInstance() {
        if (lazy == null) {
            // 在多线程环境下,不使用双重校验锁的单例会遭到破坏(会产生多个对象)
            synchronized (Lazy3.class) {
                if (lazy == null) {
                    lazy = new Lazy3();
                }
            }
        }
        return lazy;
    }

    public static void main(String[] args) {
        // 反射破坏双重校验锁
        // 如果对象不是通过单例对象获取，而是直接通过反射获取，则只允许第一次反射破坏
        // 此方式不能完全抵御反射破坏，如果反射后将flag值更改，则还是会被反射破坏
        try {
            Constructor<Lazy3> constructor = Lazy3.class.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            Lazy3 instance2 = constructor.newInstance();

            // 通过更改flag值达到破坏效果
            Field flag = EnumLazy.class.getDeclaredField("flag");
            flag.setAccessible(true);
            flag.set(instance2, false);

            Lazy3 instance3 = constructor.newInstance();

            System.out.println(instance2);
            System.out.println(instance3);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
