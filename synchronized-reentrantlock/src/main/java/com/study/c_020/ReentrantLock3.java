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
 * <p>
 * 使用reentrantlock 可以进行“尝试锁定trylock“，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class ReentrantLock3 {
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

    /**
     * 使用trylock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据trylock的返回值判定是否锁定
     * 也可以指定trylock的时间，由于trylock(timeout)抛出异常，所以要注意unlock的处理。
     */
    void m2() {
       /* boolean locked = lock.tryLock();
        System.out.println("m2 .....");
        if(locked) lock.unlock();*/
        boolean locked = false;

        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock3 r1 = new ReentrantLock3();

        new Thread(r1::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r1::m2).start();
    }
}
