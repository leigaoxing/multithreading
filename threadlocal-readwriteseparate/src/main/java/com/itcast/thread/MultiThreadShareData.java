package com.itcast.thread;

/**
 * 多个线程访问共享对象和数据的方式
 * 1.如果每个线程执行的代码相同，可以使用同一个Runnable对象，
 * 这个Runnable对象中有那个共享数据，例如，卖票系统就可以这么做。
 * 2.如果每个线程执行的代码不同，这时候需用不同的Runnable对象，有如
 * 下面两种方式实现这些Runnable对象之间的数据共享：
 * a.将共享数据封装到另外一个对象中，然后将这个对象逐一传递给各个Runnable对象.
 * 每个线程对共享数据的操作方法也分配到那个对象上去完成。这样容易实现针对
 * 该数据进行的各个操作的互斥和通信。
 * b.将这些Runnable对象作为某一个类的中的内部类，共享数据作为整个外部类的成员变量，
 * 每个线程对共享数据的操作方法也分配到外部类，以便实现对贡献数据进行的各个数据的互斥和通信，
 * 作为内部类的各个Runnable独享调用外部类的这些方法。
 * <p>
 * 设计4个线程，其中两个线程每次对j增加1，另外两个线程对j减少1，写出程序。（此例子使用的b方式。）
 *
 * @author adminitrator
 */
public class MultiThreadShareData {
    private int j;

    public synchronized void inc() {
        System.out.println(Thread.currentThread().getName() + " inc " + j);
        j++;
    }

    public synchronized void dec() {
        System.out.println(Thread.currentThread().getName() + " dec " + j);
        j--;
    }

    class Inc implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                inc();
            }

        }
    }

    class Dec implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                dec();
            }

        }
    }

    public static void main(String[] args) {
        //使用的是第2种方式的b方式
//		MultiThreadShareData mtd = new MultiThreadShareData();
//		Dec dec = mtd.new Dec();
//		Inc inc = mtd.new Inc();
//		
//		for(int i=0;i<2;i++){
//			new Thread(inc).start();
//			
//			new Thread(dec).start();
//		}
//		
        //使用第2种方式的a方式
        ShareData shareData = new ShareData();
        for (int i = 0; i < 2; i++) {

            new Thread(new RunnableInc(shareData)).start();

            new Thread(new RunnableDec(shareData)).start();
        }
    }
}

/**
 * 抽取出来的共享数据类，包含了线程调用的方法。
 *
 * @author adminitrator
 */
class ShareData {
    private int j;

    public synchronized void inc() {
        System.out.println(Thread.currentThread().getName() + " inc " + j);
        j++;
    }

    public synchronized void dec() {
        System.out.println(Thread.currentThread().getName() + " dec " + j);
        j--;
    }
}

/**
 * 实现了两个Runnable，并定义成员变量 ShareData，在调用线程时传入
 *
 * @author adminitrator
 */
class RunnableInc implements Runnable {
    private ShareData shareData;

    public RunnableInc(ShareData shareData) {
        super();
        this.shareData = shareData;
    }

    @Override
    public void run() {
        while (true)
            shareData.inc();

    }
}

class RunnableDec implements Runnable {
    private ShareData shareData;

    public RunnableDec(ShareData shareData) {
        super();
        this.shareData = shareData;
    }

    @Override
    public void run() {
        while (true)
            shareData.dec();

    }
}