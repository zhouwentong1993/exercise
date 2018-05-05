package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhouwentong on 2018/4/30.
 * 测试 cas 操作
 */
public class CasTest {
    public static final int INVOKE_TIMES = 7000;
    private static int i = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static int THOUSAND = 600;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>(THOUSAND);
        for (int j = 0; j < THOUSAND; j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < INVOKE_TIMES; k++) {
                        counter();
                        safeCounter();
                    }
                }
            });
            list.add(thread);
        }
        for (int j = 0; j < THOUSAND; j++) {
            list.get(j).start();
        }

        for (Thread thread : list) {
            thread.join();
        }
        System.out.println(i);
        System.out.println(atomicInteger.get());
    }

    private static void counter() {
        i++;
    }

    private static void safeCounter() {
        for (; ; ) {
            int i = atomicInteger.get();
            boolean b = atomicInteger.compareAndSet(i, i + 1);
            if (b) {
                break;
            }
        }

    }
}
