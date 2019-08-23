package com.study.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {
    //ArrayBlockingQueue 有界队列
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        strs.add("aaa");//如果满了，报异常
        strs.put("aaa");//满了阻塞
        strs.offer("aaa");//如果满了，不报异常，不会加进去，返回boolean值
        strs.offer("ass", 1, TimeUnit.SECONDS);//1秒钟 加不进去，就不往里加了
    }
}
