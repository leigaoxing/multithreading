package com.study.c_004;

/**
 * synchronized关键字，加在静态方法上，表示锁定这个类，而不是锁定这个对象，等同于synchronized(T.class)
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class T {
    private static int count = 10;

    public synchronized static void m() {//等同于synchronized(T.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count " + count);
    }

    public static void mm() {
        //考虑一下这里写synchronized(this)是否可以？
        // --不可以,由于静态的时候，没有this可以锁定。静态方法，静态属性是可以不用new出对象，就可以直接访问的，所以是没有this的
        synchronized (T.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count " + count);
        }
    }

}