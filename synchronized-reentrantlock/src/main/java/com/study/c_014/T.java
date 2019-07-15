package com.study.c_014;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 并不能保证多个线程共同修改running变量时，所带来的不一致问题，也就是说volatile不能替代synchronized，
 * 即volatile只保证了可见性，不能保证原子性，synchronized既可以保证可见性，又可以保证原子性。
 *
 * 对比 c_013可以在方法上加synchronized解决
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class T {
    volatile int count = 0;

    public synchronized void m() {
        System.out.println("m start...");
        for (int i = 0; i < 10000; i++) count++;
        System.out.println("m end...");
    }

    public static void main(String[] args) {
        T t = new T();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "t" + i));
        }

        threads.forEach(thread -> thread.start());

        threads.forEach(thread -> {
            try {
                //Waits for this thread to die
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }

}