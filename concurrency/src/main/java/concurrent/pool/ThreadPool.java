package concurrent.pool;

/**
 * Created by zhouwentong on 2018/5/14.
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);

}
