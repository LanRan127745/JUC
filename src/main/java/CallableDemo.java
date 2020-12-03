import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) {
        // Callable接口的实现类
        MyCallable myCallable = new MyCallable();
        // FutureTask 类继承了Runnable接口的run方法
        FutureTask futureTask = new FutureTask(myCallable);
        // 适配类
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start(); // 结果会被缓存，提高效率

        try {
            // 获取Callable的返回结果，可能会引起线程阻塞！放在最后或者使用一部通信来处理
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}
