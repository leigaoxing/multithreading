package com.study.c_026;

import java.util.concurrent.*;

public class T06_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask 实现了Runnable，Future接口
        //构造方法接收Callable
        FutureTask<Integer> task = new FutureTask<Integer>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();

        System.out.println(task.get());//阻塞

        //****************************
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });
//        System.out.println(f.get());//get执行完后，isDone是true
        System.out.println(f.isDone());
    }
}
