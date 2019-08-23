package com.study.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newCachedThreadPool();
 * 当没有线程空闲的时候，会新建线程，当有线程空闲的时候，
 * 会复用现有空闲的线程，线程在60秒没有使用就会杀死空闲的线程
 *
 * @date: 2019/7/18
 * @author: gxl
 */
public class T08_CachedPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        TimeUnit.SECONDS.sleep(80);

        System.out.println(service);
    }
}
