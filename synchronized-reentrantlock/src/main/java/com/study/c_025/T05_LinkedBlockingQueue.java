package com.study.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 高并发的情况下，可以使用Queue分两种：
 * 1. 内部加锁的，如：ConcurrentLinkedQueue
 * 2. 阻塞式队列，如：LinkedBlockingQueue,ArrayBlockingQueue
 *
 * @date: 2019/7/17
 * @author: gxl
 */
public class T05_LinkedBlockingQueue {
    //LinkedBlockingQueue 无界队列
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i);//如果满了，就会等待
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " take " + strs.take());//如果空了，就会等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }
    }

}
