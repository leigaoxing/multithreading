package com.study.c_025;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时赋值容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 *
 * @date: 2019/7/16
 * @author: gxl
 */
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists =
//                new ArrayList<>();//会出现并发问题
//                new Vector<>();
                new CopyOnWriteArrayList<>();//运行时间很长，但是有一个好处读的时候效率很高
        Random r = new Random();
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    lists.add("a" + r.nextInt(100000));
                }
            });
        }
        runAndCompute(ths);
        System.out.println(lists.size());

    }

    private static void runAndCompute(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(thread -> thread.start());
        Arrays.asList(ths).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
