package com.study.c_002;

/**
 * synchronized关键字  对某个对象加锁
 * c_001中，每次使用新new出的对象作为锁定对象，实在是太麻烦了，可以使用锁定this，也就是锁定当前对象
 *
 * @date: 2019/7/13
 * @author: gxl
 */
public class T {
    private int count = 10;

    public void m() {
        synchronized (this) {//任何线程要执行下面的代码，必须先拿到this的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count " + count);
        }
    }
}
