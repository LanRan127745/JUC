package functionInterface;

import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>(){
            @Override
            public String get() {
                return "没有参数，只有返回值";
            }
        };

        // lambda表达式
        Supplier<Integer> supplier1 = ()-> { return 1; };

        System.out.println(supplier1.get());
    }
}
