package functionInterface;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        // 工具类：输出输入的值
        Function<String,Object> function = new Function<String,Object>() {
            @Override
            public Object apply(String o) {
                return o;
            }
        };

        // lambda表达式简化
        Function<String,String> function1 = o -> {
            return o;
        };

        System.out.println(function1.apply("123"));
    }
}
