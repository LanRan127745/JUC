/* 卖票的例子
   真正的多线程开发，降低耦合性
   线程就是一个单独的资源类，没有任何附属操作，拿来即用
   1、属性、方法
 */
public class SaleTicketDemo1 {
    public static void main(String[] args) {
        //并发：多线程操作同一个资源类，把资源类丢入线程
        Ticket ticket = new Ticket();

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

class Ticket {
    // 属性
    private int number = 30;

    // 方法
    public synchronized void sale(){
        if(number > 0)
            System.out.println(Thread.currentThread().getName() + "买到了第" + number-- + "张票，剩余：" + number);
    }
}