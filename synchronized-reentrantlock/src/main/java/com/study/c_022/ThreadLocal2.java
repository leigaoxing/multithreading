package com.study.c_022;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在于ThreadLocal中，避免synchronized的使用
 * <p>
 * 运行下面程序，理解ThreadLocal
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class ThreadLocal2 {
    //    volatile static Person p = new Person();
    static ThreadLocal<Person> tl = new ThreadLocal();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }

}
