import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class CreatingComparators {
    public static void main(String[] args) {
        List<String> strings =
                Arrays.asList("one", "two", "three", "four", "five",
                        "six", "seven", "eight", "nine");
        Comparator<String> cmp =
                (s1,  s2) -> s1.compareTo(s2); //String::compareTo

        strings.sort(cmp);
        System.out.println(strings);
        //-----------------------------------------------------------------------
        Comparator<String> cmp2 =
                (s1,  s2) -> Integer.compare(s1.length(),s2.length()); //String::comparingInt
        strings.sort(cmp2);
        System.out.println(strings);

        //-----------------------------------------------------------------------
        Function<String, Integer> toLength = s -> s.length();
        Comparator<String> cmp3 =
                Comparator.comparing(toLength);
        strings.sort(cmp3);
        System.out.println(strings);

        //-----------------------------------------------------------------------
        //The previous method returns an Integer type and not an int (wrapper) type which is not efficient (=autoboxing)
        //To avoid this, we can use the following method:
        ToIntFunction<String> toLength2 = s -> s.length();
        Comparator<String> cmp4 =
                Comparator.comparingInt(toLength2);
        //comparingInt() takes a toIntFunction of T as parameter
        strings.sort(cmp4);
        System.out.println(strings);
    }
}
