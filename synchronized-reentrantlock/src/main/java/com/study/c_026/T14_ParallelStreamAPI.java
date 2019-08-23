package com.study.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 使用 parallelStream 并行执行
 *
 * @date: 2019/7/18
 * @author: gxl
 */
public class T14_ParallelStreamAPI {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < 10000; i++) {
            nums.add(100000 + r.nextInt(100000));
        }

        long start = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        nums.parallelStream().forEach(v -> isPrime(v));
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    static boolean isPrime(int num) {
        for (int i = 2; i < (int) Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
