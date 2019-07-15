package com.study.c_019;

import java.util.ArrayList;
import java.util.List;

/**
 * 曾经的面试题（淘宝？）
 * <p>
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束。
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class MyContainer2 {
    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer2 container = new MyContainer2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
            }
        }, "t1").start();

        new Thread(() -> {
            if (container.size() != 5) {
                try {
                    container.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end ");
        }, "t2").start();
    }
}
