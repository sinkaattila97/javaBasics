import java.util.function.Consumer;
import java.util.function.Supplier;

public class FirstLambdas {

    public static void main(String[] args) {
        //Supplier example - takes no parameters and returns a value with T get();
        Supplier<String> supplier = () -> "Hello World!\n";
        String string = supplier.get(); //invoking the lambda

        System.out.println(string);
        //-----------------------------------------------------
        //Consumer example - takes a parameter and returns nothing
        Consumer<String> consumer = (String s) -> System.out.println(s);
        consumer.accept("Hello World!\n"); //invoking the lambda

        //-----------------------------------------------------
        //If I want to do something else before invoking the lambda of type Supplier we use {}:
        Supplier<String> supplier2 =
                    () -> {
                        System.out.println("I am inside the Supplier");
                        return "Hello World!\n";
                    };
        System.out.println(supplier2.get());
        //-----------------------------------------------------
        //If I want to do something else before invoking the lambda of type Consumer we use {}:
        Consumer<String> consumer2 =
                (String s) -> {
                    System.out.println("I am inside the Consumer");
                    System.out.println(s);
        };
        consumer2.accept("Hello World!\n"); //invoking the lambda

    }
}
