package com.study.c_007;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 同步和非同步方法是否可以同时调用？可以
 * <p>
 * 在一个synchronized方法执行的同时非synchronized是可以同时进行的。
 * 以下就是m1锁定对象，但是m2没有锁定该对象，所以不管该对象是否有锁，m2都可以访问。
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class T {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1.start.... ");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1.end...... ");
    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() + " m2.start.... ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2.end...... ");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();
    }
}