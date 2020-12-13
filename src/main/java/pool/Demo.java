package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// Executors 工具类，创建线程池的三大方法

public class Demo {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 单个线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5); // 创建一个固定的线程池的大小
        ExecutorService threadPool = Executors.newCachedThreadPool(); // 可伸缩大小的线程池，遇强则强，遇弱则弱

        try {
            for (int i = 0; i < 100; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " -> Ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 线程池使用完毕后需要关闭
            threadPool.shutdown();
        }
    }
}
