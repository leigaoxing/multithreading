package com.itcast.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程范围内的共享变量的概念与应用
 * 概念：就是每个线程在访问一个变量时，这个变量的值是不同的，换句话说就是跟线程相关的，线程不同，变量值不同。
 *
 * @author adminitrator
 */
public class ThreadScopeShareData {
    private static Map<Thread, Integer> threadDate = new HashMap<>();
    private static int data = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() +
                            "has data: " + data);
                    threadDate.put(Thread.currentThread(), data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public int get() {
            System.out.println("A " + Thread.currentThread().getName() +
                    "has data: " + threadDate.get(Thread.currentThread()));
            return threadDate.get(Thread.currentThread());
        }
    }

    static class B {
        public int get() {
            System.out.println("B " + Thread.currentThread().getName() +
                    "has data: " + threadDate.get(Thread.currentThread()));
            return threadDate.get(Thread.currentThread());
        }
    }

    static class C {
        public int get() {
            return data;
        }
    }
}
