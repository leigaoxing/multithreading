package com.study.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newSingleThreadExecutor();
 * 线程池中只有一个线程
 * <p>
 * 保证线程的前后顺序
 *
 * @date: 2019/7/18
 * @author: gxl
 */
public class T09_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
    }
}
