import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 写入
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> myCache.put(temp + "",temp)).start();
        }

        // 读取
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> myCache.get(temp + "")).start();
        }
    }
}

/*
自定义缓存
 */
class MyCache {
    private volatile Map<String,Object> map = new HashMap<>();
    // 读写锁：更加细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // lock 这种加锁方式没有读写锁的锁的粒度细，读的时候只能有一个线程读
    private Lock lock = new ReentrantLock();

    // 存，写入的时候同时只能有一个线程占有
    // 读-写操作不可以同时进行
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入成功");
        }finally{
            readWriteLock.writeLock().unlock();
        }

    }

    // 取，读的时候可以被多个线程同时占有
    public void get(String key){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取成功");
        }finally{
            readWriteLock.readLock().unlock();
        }
    }
}