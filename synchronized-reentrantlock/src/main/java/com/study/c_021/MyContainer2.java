package com.study.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量的同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用。
 * <p>
 * 使用wait 和 notify/notifyAll来实现
 * <p>
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class MyContainer2<T> {
    private final LinkedList<T> lists = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    Condition publisher = lock.newCondition();
    Condition consumer = lock.newCondition();
    private final int MAX = 10;
    private int count = 0;

    public void put(T t) {
        try {
            lock.lock();
            while (MAX == lists.size()) {
                publisher.await();
            }
            lists.add(t);
            count++;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count--;
            publisher.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();

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
