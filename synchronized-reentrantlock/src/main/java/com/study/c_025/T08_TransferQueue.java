package com.study.c_025;

import java.util.concurrent.LinkedTransferQueue;

public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("ass");//消费者先启动，生产者先找有没有消费者，没有则阻塞，有消费者则直接消费掉
//        strs.put("aaa"); //还可以使用put往队列里放

        /*new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}
