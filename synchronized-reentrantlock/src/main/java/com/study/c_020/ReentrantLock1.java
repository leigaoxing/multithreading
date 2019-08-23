package com.study.c_020;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * reentrantlock 用于替代 synchronized
 * <p>
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class ReentrantLock1 {
    synchronized void m1() {
        IntStream.range(0, 10).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        });
    }

    synchronized void m2() {
        System.out.println("m2 .....");
    }

    public static void main(String[] args) {
        ReentrantLock1 r1 = new ReentrantLock1();

        new Thread(r1::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r1::m2).start();
    }
}
