package com.study.c_009;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，在此申请的时候仍然会得到该对象的锁。
 * 也就是说synchronized获得的锁是可以重入的
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class T {

    public synchronized void m1() {
        System.out.println("m1 start..");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end....");
        m2();
    }

    public synchronized void m2() {
        System.out.println("m2 start....");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end...");
    }
}