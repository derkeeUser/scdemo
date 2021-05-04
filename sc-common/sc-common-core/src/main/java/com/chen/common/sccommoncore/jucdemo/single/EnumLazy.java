package com.chen.common.sccommoncore.jucdemo.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: scdemo
 * @description: 枚举懒汉模式
 * @author: chenxiaoming
 * @create: 2021-05-04 14:51:44
 */
public enum EnumLazy {

    INSTANCE,
    ;

    public static EnumLazy getInstance() {
        return INSTANCE;
    }

    public void hello() {
        System.out.println("hello world");
    }
}

class EnumLazyTest {
    public static void main(String[] args) {
        EnumLazy lazy = EnumLazy.INSTANCE;
        EnumLazy lazy2 = EnumLazy.INSTANCE;

        lazy.hello();
        lazy2.hello();

        System.out.println(lazy);
        System.out.println(lazy2);


        // 尝试破坏枚举
        // 枚举不会被反射破坏
        try {
            Constructor<EnumLazy> constructor = EnumLazy.class.getDeclaredConstructor(String.class,int.class);
            constructor.setAccessible(true);
            EnumLazy lazy3 = constructor.newInstance();

            System.out.println(lazy3);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
