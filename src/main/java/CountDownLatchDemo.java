import java.util.concurrent.CountDownLatch;

// 计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 表示总数为6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " -> Go out");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //countDownLatch.await(); // 等待计数器归零，然后再向下执行

        System.out.println("众神归位");
    }
}
