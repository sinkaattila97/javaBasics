import java.util.function.Consumer;
import java.util.function.Predicate;

public class ChainingLambdas {
    public static void main(String[] args) {
        //Consumer: takes a string and does not return anything
        Consumer<String> c1 = s -> System.out.println("c1 consumes " + s);
        Consumer<String> c2 = s -> System.out.println("c2 consumes " + s);

        //Create a third consumer that consumes s with c1 then with c2
        //c1.accept("Hello");
        //c2.accept("Hello");

        //Chaining consumers
        Consumer<String> c3 = c1.andThen(c2);

        //Predicate: takes a string and returns a boolean
        Predicate<String> isNull = s -> s == null; //Simplified: Predicate<String> isNull = Objects::isNull
        System.out.println("For null = " + isNull.test(null));
        System.out.println("For null = " + isNull.test("Hello!") + "\n");

        Predicate<String> isEmpty = s -> s.isEmpty();
        System.out.println("For empty = " + isEmpty.test(""));
        System.out.println("For empty = " + isEmpty.test("Hello!") + "\n");

        //chaining predicates with .and() and .or()
        Predicate<String> p = isNull.negate().and(isEmpty.negate());
        System.out.println("For null = " + p.test(null));
        System.out.println("For empty = " + p.test("Hello!") + "\n");


    }
}
