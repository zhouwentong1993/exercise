package concurrent;

/**
 * Created by zhouwentong on 2018/5/5.
 * join 的作用：当线程 A 在线程 B 执行时调用 join 方法，意味着 B 需要等到 A 执行完毕再执行。记忆的话可以通过主线程实例、
 */
public class ThreadJoinTest {
    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread current = new Thread(new Domino(previous), String.valueOf(i));
            current.start();
            previous = current;
        }
    }

    static class Domino implements Runnable{
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 终止");
        }
    }
}
