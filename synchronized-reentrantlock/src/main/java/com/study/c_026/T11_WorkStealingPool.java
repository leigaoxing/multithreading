package com.study.c_026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 顾名思义，WorkStealingPool，工作窃取线程池，
 * 某个线程工作完成之后会自己找未完成的任务执行
 *
 * since 1.8
 * @date: 2019/7/18
 * @author: gxl
 */
public class T11_WorkStealingPool {

    public static void main(String[] args) throws IOException, InterruptedException {
        //TODO  newFixedThreadPool有啥区别，这一线程池，会有一个未执行任务队列，执行完之后会在队列接着拿任务执行
        ExecutorService service = Executors.newWorkStealingPool();
        //查看电脑是几核的，默认启动跟这个相同的线程
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(service);

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(service);

        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println(service);

        TimeUnit.SECONDS.sleep(2);
        System.out.println(service);
        //由于产生的精灵线程（守护线程，后台线程），主线程不阻塞的话，看不到结果。
        System.in.read();

    }

    static class R implements Runnable {
        long runTime;

        public R(long runTime) {
            this.runTime = runTime;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(runTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
