package blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
/*
同步队列
 */
public class synchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                for (int i = 0; i < 25; i++) {
                    System.out.println(Thread.currentThread().getName() + " -> put " + i);
                    blockingQueue.put(String.valueOf(i));
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 25; i++) {
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.println(Thread.currentThread().getName() + " -> take " + blockingQueue.take());
            }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
