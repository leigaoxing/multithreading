package com.study.c_017;

import java.util.concurrent.TimeUnit;

/**
 * 锁定对象o，如果o的属性发生变化，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定的引用变成另外的对象
 * <p>
 * 这个示例也证明了，锁的是堆里的对象，而不是栈里面的引用
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class T {
    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();

        //启动第一个线程
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //修改锁定对象
        t.o = new Object();
        //启动第二个线程
        new Thread(t::m, "t2").start();
    }
}