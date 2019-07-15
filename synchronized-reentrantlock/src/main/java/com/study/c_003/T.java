package com.study.c_003;

/**
 * synchronized关键字 对某个对象加锁
 * 直接加在 方法上 ，等同于c_002中在方法体中加synchronized(this)
 *
 * @date: 2019/7/14
 * @author: gxl
 */
public class T {
    private int count = 10;

    public synchronized void m() {//等同于在方法的diamante执行时要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count " + count);
    }
}