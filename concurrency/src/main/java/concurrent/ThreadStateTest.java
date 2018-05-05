package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhouwentong on 2018/5/5.
 * 线程的状态
 */
public class ThreadStateTest {

    public static void main(String[] args) {
        new Thread(new Sleep(),"timeWaiting thread").start();
        new Thread(new Block(),"Block thread-01").start();
        new Thread(new Block(),"Block thread-02").start();
        new Thread(new Wait(),"waiting thread").start();
    }

    static class Sleep implements Runnable{

        @Override
        public void run() {
            while (true) {
                sleepTime(1000L);
            }
        }
    }

    static class Block implements Runnable {

        @Override
        public void run() {
            synchronized (Block.class) {
                while (true) {
                    sleepTime(1000);
                }
            }
        }
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Wait.class) {
                    try {
                        Wait.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void sleepTime(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
