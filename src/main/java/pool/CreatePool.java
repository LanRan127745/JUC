package pool;

import java.util.concurrent.*;

public class CreatePool {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy() //ThreadPool默认的拒绝策略，当阻塞队列已满，还有Runnable 对象要处理，不受理这个对象，并抛出异常
        );
    }
}
