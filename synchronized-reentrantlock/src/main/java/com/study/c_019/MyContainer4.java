package com.study.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题（淘宝？）
 * <p>
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束。
 * <p>
 * 这里使用wait和notify做到，wait释放锁，notify不会释放锁
 * 需要注意的是，运行这种方法，必须要保证t2先执行，也就是首先让t2监听才可以。
 * <p>
 * 阅读下面的程序，并分析结果
 * 可以读到输出结果并不是size=5时退出t2，而是t1结束时t2才收到通知而退出
 * 想想这是为什么
 * <p>
 * notify之后，t1必须释放锁，t2退出后，也不许notify，通知t1继续进行
 * 整个通信过程比较复杂
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class MyContainer4 {
    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer4 container = new MyContainer4();

        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {//这里要使用wait，notify，必须使用synchronized，进行同步，两个方法是对锁对象进行的。
                System.out.println("t2 start ");
                if (container.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end ");
                lock.notify();
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1 start.. ");
                for (int i = 0; i < 10; i++) {
                    container.add(new Object());
                    System.out.println("add.....");
                    if (container.size() == 5) {
                        lock.notify();
                        //由于notify不释放锁，所以要使用wait释放锁，让t2继续执行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1 end ...");
            }
        }, "t1").start();


    }
}
