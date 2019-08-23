package com.study.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();//容量为0 的队列
        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        strs.add("aaa");//直接使用add会报异常，因为queue的容量为0
        strs.put("ass");//阻塞等待消费者消费
        System.out.println(strs.size());
    }
}
