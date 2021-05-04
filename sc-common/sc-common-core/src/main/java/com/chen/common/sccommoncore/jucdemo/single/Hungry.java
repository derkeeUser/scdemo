package com.chen.common.sccommoncore.jucdemo.single;

/**
 * @program: scdemo
 * @description: 饿汉模式
 * @author: chenxiaoming
 * @create: 2021-05-04 14:49:58
 */
public class Hungry {

    private static final Hungry HUNGRY = new Hungry();

    private Hungry() {}

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
