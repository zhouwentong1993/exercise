package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhouwentong on 2018/4/30.
 * 测试死锁
 */
public class DeadLockTest {
    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        Lock reentrantLock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("AAAAA");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("BBBBBBB");
                    }
                }
            }
        }).start();

    }


}
