package com.study.c_024;

import java.util.ArrayList;
import java.util.List;

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
public class TicketSeller1 {
    static List<String> tickets = new ArrayList<>();//不是同步容器，会导致重复销售，超量销售

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //这里size（） 方法和remove 方法两个过程 之间很容易被线程趁机而入
                while (tickets.size() > 0) {
                    System.out.println("销售了....." + tickets.remove(0));
                }
            }).start();
        }
    }
}
