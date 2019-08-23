package com.study.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个固定容量的同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用。
 * <p>
 * 使用wait 和 notify/notifyAll来实现
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class MyContainer1<T> {
    private final LinkedList<T> lists = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (MAX == lists.size()) {//这里的判断要使用while
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        count++;
        this.notifyAll();


    }

    public synchronized T get() {
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = lists.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();

        //启动消费者
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " " + c.get());
                System.out.println("c end");
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //启动生产者
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                    System.out.println(Thread.currentThread().getName() + " " + j);
                }
                System.out.println("p end");
            }, "p" + i).start();
        }
    }

}
