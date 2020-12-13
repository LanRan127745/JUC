package functionInterface;

import java.util.function.Predicate;
// 断定型接口：有一个输入参数，返回值只能是布尔值
public class PredicateDemo {
    public static void main(String[] args) {
        // 判断字符串是否为空
        Predicate<String> predicate = new Predicate<String>(){
            @Override
            public boolean test(String s) {
                return toString().isEmpty();
            }
        };

        // lambda表达式
        Predicate<String> predicate1 = s -> {
                return s.isEmpty();
        };

        System.out.println(predicate.test("asd"));
    }
}
