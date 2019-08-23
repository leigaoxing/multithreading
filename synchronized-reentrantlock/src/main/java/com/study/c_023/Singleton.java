package com.study.c_023;

import java.util.Arrays;

/**
 * 线程安全的单例模式
 * <p>
 * 阅读文章：https://www.cnblogs.com/xudong-bupt/p/3433643.html
 * <p>
 * 更好的是采用下面的方式，既不用加锁，也不用懒加载
 *
 * @date: 2019/7/16
 * @author: gxl
 */
public class Singleton {
    private Singleton() {
        System.out.println("singleton...");
    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    public static Singleton getInstance() {
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[200];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                Singleton.getInstance();
            });
        }
        Arrays.asList(ths).forEach(thread -> thread.start());
    }
}
