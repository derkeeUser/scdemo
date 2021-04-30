package com.chen.common.sccommoncore.jucdemo.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: scdemo
 * @description: 阻塞队列ArrayBlockingQueue的使用,FIFO(先进先出)
 * @author: chenxiaoming
 * @create: 2021-04-30 10:06:37
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //test1();
        //test2();
        //test3();
        test4();
    }

    /**
     * 抛出异常：add()/remove()
     */
    public static void test1() {
        int total = 3;
        ArrayBlockingQueue queue = new ArrayBlockingQueue(total);
        System.out.println(queue.add("A"));
        System.out.println(queue.add("B"));
        System.out.println(queue.add("C"));
        System.out.println(queue.add("D"));

        System.out.println("================");
        System.out.println("查看队首：" + queue.element());

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

    }

    /**
     * 有返回值，不抛异常：offer()/poll()可设置超时时间
     */
    public static void test2() {
        int total = 3;
        ArrayBlockingQueue queue = new ArrayBlockingQueue(total);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println(queue.offer("C"));
        System.out.println(queue.offer("D"));

        System.out.println("================");
        System.out.println("查看队首：" + queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }


    /**
     * 阻塞，一直等待：put()/take()
     */
    public static void test3() throws InterruptedException {
        int total = 3;
        ArrayBlockingQueue queue = new ArrayBlockingQueue(total);
        queue.put("A");
        queue.put("B");
        queue.put("C");

        System.out.println("================");

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());

    }

    /**
     * 阻塞，等待超时退出：offer(x,x,x)/poll(x,x)
     */
    public static void test4() throws InterruptedException {
        int total = 3;
        ArrayBlockingQueue queue = new ArrayBlockingQueue(total);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println(queue.offer("C"));
        System.out.println(queue.offer("D", 3, TimeUnit.SECONDS));

        System.out.println("================");
        System.out.println("查看队首：" + queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll(3, TimeUnit.SECONDS));


    }
}
