package com.study.c_006;

/**
 * 分析下这个线程的输出
 * run方法在没加上synchronized之前：
 * 第一个线程执行count--,未进行打印；
 * 第二个线程这时候进入进行count--，未进行打印，
 * 第三个线程这时候进行count--，然后三个线程这时候同时打印结果，这时候结果都是7；
 * <p>
 * 为防止这种错误，在run方法上加入synchronized，进行加锁。
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class T implements Runnable {
    private int count = 10;

    @Override
    public synchronized void run() {//一个synchronized代码块是原子操作，不可分
        count--;
        System.out.println(Thread.currentThread().getName() + " count " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}