package com.study.c_024;

import java.util.ArrayList;
import java.util.List;
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
public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();//换成同步容器 一样会出现TicketSeller1一样的问题，由于两个原子操作，中间还是会被线程趁入

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
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了....." + tickets.remove(0));
                }
            }).start();
        }
    }
}
