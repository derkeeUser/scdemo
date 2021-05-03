package com.chen.common.sccommoncore.jucdemo.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @program: scdemo
 * @description: volatile方式：保证可见性
 * @author: chenxiaoming
 * @create: 2021-05-03 17:53:44
 */
public class VolatileDemo {
    /**
     * 此处不加volatile时，程序运行后死循环，因为不保证可见性。反之不会死循环
     */
    private static volatile int num = 0;

    public static void main(String[] args) {

        // 因为保证可见性，运行后不会死循环
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;

        System.out.println(num);
    }
}
