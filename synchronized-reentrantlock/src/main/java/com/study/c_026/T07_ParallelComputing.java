package com.study.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行计算，把任务分成一个一个小任务，然后再将小任务结果汇总
 *
 * @date: 2019/7/17
 * @author: gxl
 */
public class T07_ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        List<Integer> prime = getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        final int cpuCoreNum = 4;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

        Future<List<Integer>> f1 = service.submit(new MyTask(1, 80000));
        Future<List<Integer>> f2 = service.submit(new MyTask(80001, 130000));
        Future<List<Integer>> f3 = service.submit(new MyTask(130001, 170000));
        Future<List<Integer>> f4 = service.submit(new MyTask(170001, 200000));

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrime(startPos, endPos);
        }
    }

    //求素数（质数）
    static boolean isPrime(int num) {
        for (int i = 2; i < (int) Math.sqrt(num) / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) results.add(i);
        }
        return results;
    }
}
