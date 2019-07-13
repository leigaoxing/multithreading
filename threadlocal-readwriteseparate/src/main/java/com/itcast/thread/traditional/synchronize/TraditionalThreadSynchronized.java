package com.itcast.thread.traditional.synchronize;

public class TraditionalThreadSynchronized {
    /**
     * 锁 一定要锁 同一个对象 synchronized(this){} 代码块
     * 可以将synchronized 修饰普通方法 就相当于使用上面的使用this的同步代码块。
     * <p>
     * 静态方法的同步 是锁class类，而不是对象。
     *
     * @author adminitrator
     */
    static class Outputer {
        public void output(String name) {
            int len = name.length();
            synchronized (Outputer.class) {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }

        public synchronized void output2(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        public static synchronized void output3(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    private void init() {
        final Outputer output = new Outputer();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                output.output("lis");
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Outputer.output3("zhangs");
            }
        }).start();
    }
}
