package com.study.c_011;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，默认情况锁会释放
 * 所以，在并发处理的过程中，有异常应多加小心，不然可能会发生不一致的情况。
 * <p>
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适，
 * 在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能访问到异常产生的数据，
 * 因此要非常小心的处理同步业务逻辑的异常。
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class T {
    private int count = 0;

    public synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start ");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                int i = 1 / 0;//此处抛出异常，锁将被释放，要不被释放，可以在这类进行try-catch，然后循环继续
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //在 m()方法发生异常后，t1释放锁，t2才得到锁，继续执行
        //如果不想释放锁，那么加上try-catch处理异常
        new Thread(t::m, "t2").start();
    }

}