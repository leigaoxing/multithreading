package com.study.c_008;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题（dirty read）
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class Account {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    //在读方法是否加锁，根据具体的业务逻辑，看业务是否允许脏读，允许则不加锁，不允许则加锁，即读方法加synchronized关键字
    public double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        Account a = new Account();

        new Thread(() -> a.set("张三", 100.00)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("张三"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("张三"));
    }
}