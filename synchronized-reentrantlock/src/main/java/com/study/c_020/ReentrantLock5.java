package com.study.c_020;

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
 * <p>
 * ReentrantLock 还可以指定为公平锁
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class ReentrantLock5 extends Thread {
    private static ReentrantLock lock = new ReentrantLock(true);//参数为true表示为公平锁，请对比输出结果

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁" + i);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 r1 = new ReentrantLock5();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();
    }
}
