package com.study.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * 高并发的情况下，可以使用Queue分两种：
 * 1. 内部加锁的，如：ConcurrentLinkedQueue
 * 2. 阻塞式队列，如：LinkedBlockingQueue,ArrayBlockingQueue
 * @date: 2019/7/17
 * @author: gxl
 */
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.offer("a" + i);
        }
        System.out.println(queue);
        System.out.println(queue.size());
//      取出来并删除
        System.out.println(queue.poll());
        System.out.println(queue.size());
//      只取出来不删除
        System.out.println(queue.peek());
        System.out.println(queue.size());
    }
}
