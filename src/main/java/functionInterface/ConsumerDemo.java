package functionInterface;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>(){
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        // lambda表达式简化
        Consumer<String> consumer1 = str -> { System.out.println(str); };

   consumer1.accept("啊呀");
    }
}
