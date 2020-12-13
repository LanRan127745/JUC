package blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException{
        test4();
    }
    /*
    抛出异常
     */
    public static void test1() {
        // 队列大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        // 添加第四个元素时抛出了异常：IllegalStateException: Queue full
        // System.out.println(blockingQueue.add("d"));

        // 弹出全部元素
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        // 当队列为空时，使用remove弹出元素时会抛出异常：java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());

        // 队列为空，获取队首元素抛出异：java.util.NoSuchElementException
        System.out.println(blockingQueue.element());
    }

    /*
    不抛出异常
     */
    public static void test2() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        // 添加第4个元素不抛出异常，返回false
        System.out.println(blockingQueue.offer("d"));

        System.out.println("====================================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        // 队列为空不抛出异常，返回null
        System.out.println(blockingQueue.poll());

        // 队列为空，获取队首元素不抛出异常，返回null
        System.out.println(blockingQueue.peek());
    }

    /*
    等待，阻塞（一直阻塞）
     */
    public static void test3() throws InterruptedException{
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        // 没有返回值
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        //队列已满，一直处于阻塞状态
        //blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        //队列已空，没有元素可弹出，一直处于阻塞状态
        System.out.println(blockingQueue.take());
    }

    /*
    等待，阻塞（超时等待）
     */
    public static void test4() throws InterruptedException{
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS)); // 等待超时两秒就退出

        System.out.println("===============");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS)); //超时等待两秒退出

    }
}
