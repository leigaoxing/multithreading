package com.itcast.thread.traditional;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器java.util.Timer类的schedule方法中定义。先知道这些，之后再接着搞。
 *
 * @author adminitrator
 */
public class TraditionalTimer {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("bombing");
            }
        }, 10000, 3000);

        while (true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
