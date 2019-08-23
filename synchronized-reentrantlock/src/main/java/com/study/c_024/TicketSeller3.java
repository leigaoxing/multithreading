package com.study.c_024;

import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票有个一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * @date: 2019/7/16
 * @author: gxl
 */
public class TicketSeller3 {
    static LinkedList<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (tickets) {//这里加把所，将操作放在锁里面一同执行，保证原子性
                    while (true) {
                        if (tickets.size() < 0) break;
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("销售了....." + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
