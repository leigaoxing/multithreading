package com.study.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock 用于替代 synchronized
 * <p>
 * 使用reentrantLock 可以完成同样的功能
 * 需要注意的是，必须要 手动释放锁。
 * 使用synchronized锁定的话，如果遇到异常，jvm会自动释放锁，但是lock必须手动释放，因此经常在finally中进行锁的释放。
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();//synchronized(this)
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2 .....");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock2 r1 = new ReentrantLock2();

        new Thread(r1::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r1::m2).start();
    }
}
