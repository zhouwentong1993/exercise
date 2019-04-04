package com.wentong.demo;

import java.util.concurrent.TimeUnit;

/**
 * 主线程死之后子线程还占用资源吗
 */
public class MainThreadDie {
    public static void main(String[] args) throws Exception{

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(30);
        System.out.println("main thread die");

    }
}
