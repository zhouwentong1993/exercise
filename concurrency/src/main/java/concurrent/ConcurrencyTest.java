package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Java 并发编程实战代码
 *
 * @author zhouwentong
 */
public class ConcurrencyTest {
    private static long count = 10000000000L;

    public static void main(String[] args) throws Exception {
        concurrent();
        serial();

    }

    public static void concurrent() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        final long[] a = {0};
        final long[] start = {System.currentTimeMillis()};
        for (int i = 0; i < 100; i++) {
            int startIndex = (int) ((i) * (count / 100));
            int endIndex = (int) ((i + 1) * (count / 100));
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    System.out.println("i is " + finalI + " startIndex is " + startIndex + " endIndex is " + endIndex + " currentTime is " + start);
                    for (int i = startIndex; i < endIndex; i++) {
//                        a.incrementAndGet();
                        a[0] = a[0] + 1;
                    }
                    System.out.println("i is " + finalI + " startIndex is " + startIndex + " endIndex is " + endIndex + " waste time is  " + (System.currentTimeMillis() - start));
                    countDownLatch.countDown();
                    System.out.println("Now lock count is " + countDownLatch.getCount() + " and i is " + finalI);
                }
            });
            System.out.println("thread" + i + " start!");
            thread.start();
        }
        countDownLatch.await();
        System.out.println(a[0]);

//        int b = 0;
//        for (int i = 0; i < count; i++) {
//            b--;
//        }
        System.out.println(System.currentTimeMillis() - start[0]);
    }

    public static void serial() throws Exception {
        long start = System.currentTimeMillis();
        long[] a = {0};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    a[0] = a[0] + 1;
                }
            }
        });
        thread.start();
        thread.join();

//        int b = 0;
//        for (int i = 0; i < count; i++) {
//            b--;
//        }
        System.out.println(a[0]);
        System.out.println(System.currentTimeMillis() - start);

    }

}
