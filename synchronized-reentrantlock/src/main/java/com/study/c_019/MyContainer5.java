package com.study.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 曾经的面试题（淘宝？）
 * <p>
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束。
 * <p>
 * 这里使用wait和notify做到，wait释放锁，notify不会释放锁
 * 需要注意的是，运行这种方法，必须要保证t2先执行，也就是首先让t2监听才可以。
 * <p>
 * 阅读下面的程序，并分析结果
 * 可以读到输出结果并不是size=5时退出t2，而是t1结束时t2才收到通知而退出
 * 想想这是为什么
 * <p>
 * notify之后，t1必须释放锁，t2退出后，也不许notify，通知t1继续进行
 * 整个通信过程比较复杂
 * <p>
 * 使用Latch（门闩）替代wait，notify来进行通知
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countdown方法替代wait和notify
 * CountDownLatch不涉及锁定，当count值为零时，当前线程继续执行
 * <p>
 * 当不涉及同步，只是涉及线程通信的时候，用synchronized + wait/notify 就显得太重了。
 * 这时应该考虑CountDownLatch/CyclicBarrier/Semaphore
 *
 * @date: 2019/7/15
 * @author: gxl
 */
public class MyContainer5 {
    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer5 container = new MyContainer5();
        //可参考：https://mrbird.cc/JUC-CountDownLatch.html
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 start ");
            if (container.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end ");
        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1 start.. ");
            IntStream.range(0, 10).forEach(i -> {
                container.add(new Object());
                System.out.println("add.....");
                if (container.size() == 5) {
                    latch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("t1 end ...");
        }, "t1").start();
    }
}
