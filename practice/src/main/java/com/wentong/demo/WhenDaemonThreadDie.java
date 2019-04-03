package com.wentong.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 守护线程顾名思义，就是守护用户线程的工具。
 * 其他方面和用户线程几乎一样，守护线程可以用来做监控，比如 Tomcat 就是通过让用户线程死亡来达到平稳死亡的目的
 * 只要全部的用户线程死掉，守护线程也就死了
 *
 */
public class WhenDaemonThreadDie {
    public static void main(String[] args) throws Exception{
        Thread daemonThread1 = new Thread(() -> {
            print(Thread.currentThread().getName());
        });
        daemonThread1.setName("daemonThread1");
        daemonThread1.setDaemon(true);
        Thread daemonThread2 = new Thread(() -> {
            print(Thread.currentThread().getName());
        });
        daemonThread2.setName("daemonThread2");
        daemonThread2.setDaemon(true);

        daemonThread1.start();
        daemonThread2.start();
        print("main");

        Random random = new Random();
        while (true) {
            int i = random.nextInt(10);
            if (i == 8) {
                System.out.println("main stop!");
                return;
            } else {
                TimeUnit.SECONDS.sleep(1);
            }
        }

    }

    private static void print(String name) {
        Thread thread = new Thread(() -> {

            int i = 0;
            while (true) {
                i++;
                System.out.println(name + "->" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }
}
