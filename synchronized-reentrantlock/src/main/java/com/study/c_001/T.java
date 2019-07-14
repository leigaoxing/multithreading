package com.study.c_001;

/**
 * synchronized关键字  对某个对象加锁
 *
 * @date: 2019/7/13
 * @author: gxl
 */
public class T {
    private int count = 10;
    private Object o = new Object();

    public void m() {
        synchronized (o) {//任何线程要执行下面的代码，必须先拿到o的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count " + count);
        }
    }
}
