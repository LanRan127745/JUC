package utilClass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 集齐7颗龙珠召唤神龙
         */
        // 召唤龙珠的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(8,() -> {
            System.out.println("召唤神龙成功");
        });

        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(() -> {
                //lambda 如何操作i变量 ===>内部类的知识点
                System.out.println(Thread.currentThread().getName() + "收集到了第" + temp + "颗龙珠");
                try {
                    // 等待7个线程执行完毕，线程每执行完一个，parties + 1,直到parties到7
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
