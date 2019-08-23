package com.study.c_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool 接收的任务一般是 ForkJoinTask；
 * RecursiveAction 是 ForkJoinTask的实现类，没有返回值
 * RecursiveTask 是ForkJoinTask 的实现类，有返回值。
 * <p>
 * since 1.7
 *
 * @date: 2019/7/18
 * @author: gxl
 */
public class T12_ForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 100000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());
    }

    static class AddTask extends RecursiveAction {
        int start, end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {

            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("Start:" + start + " ,end: " + end);
            } else {
                int middle = start + (end - start) / 2;
                AddTask task = new AddTask(start, middle);
                AddTask task1 = new AddTask(middle, end);
                task.fork();
                task1.fork();
            }

        }
    }

    /*static class AddTask extends RecursiveTask<Long> {
        int start, end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("Start:" + start + " ,end: " + end);
                return sum;
            } else {
                int middle = (end + start) / 2;
                AddTask task = new AddTask(start, middle);
                AddTask task1 = new AddTask(middle, end);
                task.fork();
                task1.fork();
                return task.join() + task1.join();
            }
        }
    }*/

    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask at = new AddTask(0, nums.length);
        fjp.execute(at);
//        Long result = at.join();
//        System.out.println(result);

        System.in.read();
    }
}
