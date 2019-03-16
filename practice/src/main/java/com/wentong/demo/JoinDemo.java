package com.wentong.demo;

import java.util.concurrent.TimeUnit;

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("I am Thread1");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("I am Thread2");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();


    }
}
