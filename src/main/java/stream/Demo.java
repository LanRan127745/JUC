package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
对六个用户进行筛选：
1、ID必须是偶数
2、年龄必须大于19岁
3、用户名转为大写字母
4、用户名字母倒着排序
5、只输出一个用户
 */
public class Demo {
    public static void main(String[] args) {
        User user1 = new User(1,"jack",18);
        User user2 = new User(2,"kangkang",19);
        User user3 = new User(3,"Michael",20);
        User user4 = new User(4,"Jane",21);
        User user5 = new User(5,"zzw",22);
        User user6 = new User(6,"pp",23);

        List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6);

        list.stream()
                .filter(user -> {return user.getId() % 2 == 0;}) //过滤
                .forEach(System.out::println);
        
    }
}
