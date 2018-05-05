package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouwentong on 2018/5/5.
 * 测试 wait 和 notify 相关的
 * wait 和 notify 是和锁相关的
 * 在 notify 唤醒的时候，wait 不会立即变为可执行。需要等 notify 线程释放 wait 所持有的锁之后，进入到等待执行队列中，然后才能得到执行机会。
 */
public class WaitNotifyTest {
    private static Object lock = new Object();
    private static boolean flag = true;

    public static void main(String[] args) {
        new Thread(new Wait(), "wait thread").start();
        sleepSeconds(1);
        new Thread(new Notify(), "notify thread").start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                while (flag) {
                    System.out.println(Thread.currentThread() + " flag is true, wait @ " + simpleDateFormat.format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false, wait @ " + simpleDateFormat.format(new Date()));

            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                System.out.println(Thread.currentThread() + " flag is false, notify @ " + simpleDateFormat.format(new Date()));
                lock.notify();
                flag = true;
                sleepSeconds(5);
            }

            synchronized (lock) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                System.out.println(Thread.currentThread() + " flag is false,hold again notify @ " + simpleDateFormat.format(new Date()));
                sleepSeconds(5);
            }
        }
    }

    public static void sleepSeconds(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
