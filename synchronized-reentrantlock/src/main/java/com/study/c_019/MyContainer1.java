package com.study.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题（淘宝？）
 * <p>
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束。
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class MyContainer1 {
    //这里没有可见性，所以t1会一直执行，即使t2做了判断，永远只是在开始的时候得到的list的大小
    List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer1 container = new MyContainer1();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                System.out.println("add....");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (container.size() == 5) {
                    System.out.println(container.size() + "....");
                    break;
                }
            }

            System.out.println("t2 end ");
        }, "t2").start();
    }
}
