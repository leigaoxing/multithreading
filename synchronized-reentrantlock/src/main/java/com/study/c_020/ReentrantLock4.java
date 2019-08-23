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
 * <p>
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待的过程中，可以被打断
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class ReentrantLock4 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1.start....");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1.end....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        Thread t2 = new Thread(() -> {
            boolean locked = false;
            try {
//                lock.lock();
                lock.lockInterruptibly();//可以对t2.interrupt();做出响应
                locked = true;
                System.out.println("t2.start....");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t2.end....");
            } catch (InterruptedException e) {
                System.out.println("t2 interrupt.....");
            } finally {
                if (locked) lock.unlock();
            }
        }, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();
    }
}
