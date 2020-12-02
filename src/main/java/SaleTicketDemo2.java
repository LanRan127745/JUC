import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 卖票的例子
 */
public class SaleTicketDemo2 {
    public static void main(String[] args) {
        //并发：多线程操作同一个资源类，把资源类丢入线程
        Ticket2 ticket = new Ticket2();

        //@FunctionalInterface->函数式接口
        //JDK1.8之后->lambda表达式
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}

class Ticket2 {
    // 属性
    private int number = 30;

    Lock l = new ReentrantLock();
    // 方法
    public synchronized void sale(){
        if(number > 0)
            System.out.println(Thread.currentThread().getName() + "买到了第" + number-- + "张票，剩余：" + number);
    }
}