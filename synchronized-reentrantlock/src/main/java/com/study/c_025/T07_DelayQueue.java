package com.study.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @date: 2019/7/17
 * @author: gxl
 */
public class T07_DelayQueue {
    //无界队列
    static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    //DelayQueue 在进行put的时候要求元素实现Delayed接口，
    //delayQueue先执行等待时间长的任务
    //delayQueue可以用来做定时执行任务
    static class MyTask implements Delayed {
        long runningTime;

        public MyTask(long runningTime) {
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return String.valueOf(runningTime);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask task1 = new MyTask(now + 1000);
        MyTask task2 = new MyTask(now + 2000);
        MyTask task3 = new MyTask(now + 3000);
        MyTask task4 = new MyTask(now + 4000);
        MyTask task5 = new MyTask(now + 5000);

        tasks.put(task1);
        tasks.put(task2);
        tasks.put(task3);
        tasks.put(task4);
        tasks.put(task5);
        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
