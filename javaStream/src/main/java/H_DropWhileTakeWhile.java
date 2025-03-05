import java.util.ArrayList;
import java.util.stream.Stream;

public class H_DropWhileTakeWhile {
    public static void main(String[] args) {
        Class<?> clzz = ArrayList.class;

        clzz.getSuperclass();

        Stream.<Class<?>>iterate(clzz, c -> c.getSuperclass())
                .takeWhile(c -> c != null) //we need this because Object has no superclass and we will get NullPointerException
                .forEach(System.out::println);
    }
}
