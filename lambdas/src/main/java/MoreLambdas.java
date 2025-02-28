import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MoreLambdas {
    public static void main(String[] args) {
        List<String> strings =
                new ArrayList<>(List.of("one ", "two ", "tree ", "four ", "five ")); //need ArrayList because Lis is immutable and removeIf() is used

        Predicate<String> filter =
                (String s) -> s.startsWith("t");
        //Simplified: s -> s.startsWith("t");
        strings.removeIf(filter);

        //to print the list
        Consumer<? super String> action =
                (String string) -> System.out.print(string);
        //Simplified: string -> System.out.print(string);
        strings.forEach(action);
    }
}
