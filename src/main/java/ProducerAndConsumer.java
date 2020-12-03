import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        Data data = new Data();
        // A -> B -> C 按顺序执行
        new Thread(() -> { for (int i = 0; i < 10; i++) { data.printA(); } },"A").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) { data.printB(); } },"B").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) { data.printC(); } },"C").start();

        // JUC 并发线程包下的类的方法
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Data {
    private Lock lock = new ReentrantLock(); // 创建锁
    private Condition condition1 = lock.newCondition(); // 创建同步监视器
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1;


    public void printA(){
        lock.lock();
        try {
            // 业务代码 判断 -> 执行 -> 通知
            while(number != 1){
                // 线程进入等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "线程正在执行！");
            number = 2;
            // 唤醒指定的线程 -> B
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            while(number != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "线程正在执行！！");
            number = 3;
            // 唤醒指定线程 -> C
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            while(number != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "线程正在执行！！！");
            number = 1;
            // 唤醒指定线程 -> C
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}