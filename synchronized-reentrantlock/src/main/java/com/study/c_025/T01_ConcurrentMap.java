package com.study.c_025;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * 阅读ConcurrentSkipListMap
 *
 * @date: 2019/7/16
 * @author: gxl
 */
public class T01_ConcurrentMap {
    public static void main(String[] args) {
//        Map<String, String> map = new ConcurrentHashMap<>();
//        Map<String,String> map = new ConcurrentSkipListMap<>();//高并发且排序

        Map<String, String> map = new Hashtable<>();//效率很低的同步容器，现在很少用了
//        Map<String, String> map = new HashMap<>();//手动加锁-Collections.synchronizedXXX
//        Map<String,String> map = new TreeMap<>();//非高并发排序

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(10000), "a" + r.nextInt(10000));
                }
                countDownLatch.countDown();
            });
        }

        Arrays.asList(ths).forEach(thread -> thread.start());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
