package com.study.c_010;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，在此申请的时候仍然会得到该对象的锁。
 * 也就是说synchronized获得的锁是可以重入的
 * <p>
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class T {

    public synchronized void m() {
        System.out.println("m start..");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end....");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

class TT extends T {
    @Override
    public synchronized void m() {
        System.out.println("child m start..");
        super.m();
        System.out.println("child m end....");
    }
}