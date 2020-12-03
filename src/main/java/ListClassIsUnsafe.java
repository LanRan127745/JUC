import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// ConcurrentModificationException 并发修改异常！
public class ListClassIsUnsafe {
    public static void main(String[] args) {
        // 并发下 ArrayList 是不安全的
        //List<String> list = new ArrayList<>();
        /*解决方案：
        1、使用线程安全的集合：List<String> list = new Vector<>();
        2、List<String> list = Collections.synchronizedList(new ArrayList<>());
        3、List<String> list = new CopyOnWriteArrayList<>();
         */
        Set h = new HashSet();
        // CopyOnWrite：写入时复制，简称COW，是计算机程序设计领域的一种优化策略
        // 多个线程调用的时候，list读取的时候是固定的，写入的时候可能存在覆盖操作
        // 在写入的时候避免覆盖，造成数据问题！

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
                },String.valueOf(i)).start();
        }
    }
}
