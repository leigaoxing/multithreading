package com.study.c_015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身分法都是原子性的，单不能保证过个方法连续调用时原子性的
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class T {
    //    volatile int count = 0;
    AtomicInteger atomCount = new AtomicInteger(0);

    public /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
            atomCount.incrementAndGet();//count++;
        }
    }

    public static void main(String[] args) {
        T t = new T();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread" + i));
        }
        threads.forEach(thread -> thread.start());

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.atomCount);
    }

}