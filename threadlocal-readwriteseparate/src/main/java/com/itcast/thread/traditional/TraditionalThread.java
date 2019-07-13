package com.itcast.thread.traditional;

/**
 * 实现线程的两种方式：1 继承Thread类，重写run方法
 *                     2 实现Runable接口
 *
 * @author adminitrator
 */
public class TraditionalThread {
    public static void main(String[] args) {
        //1. 继承Thread类，重写run方法
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1-->" + Thread.currentThread().getName());
                }
            }

        }.start();

        //2.传入Runnable对象
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2-->" + Thread.currentThread().getName());
            }
        }).start();

        //3. 即传入Runnable对象，又继承Thread类，重写了run方法
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3-->runnable" + Thread.currentThread().getName());
            }
        }) {

            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("4-->thread" + Thread.currentThread().getName());
                }
            }
        }.start();
    }
}
