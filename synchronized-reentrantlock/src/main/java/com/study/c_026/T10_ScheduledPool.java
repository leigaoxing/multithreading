package com.study.c_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newScheduledThreadPool(4);
 * <p>
 * Timer 是每个定时任务，启用新的线程，
 * <p>
 * Executors.newScheduledThreadPool(4)，
 * 可以复用线程
 *
 * @date: 2019/7/18
 * @author: gxl
 */
public class T10_ScheduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
        //initialDelay:0第一个线程没有延迟，period：500，每隔500ms执行一次，
        // 如果500ms该线程没有执行完，这调用另一个线程，如果4个线程都在执行，
        // 则延迟调用，等待已有线程执行完后在执行
    }
}
