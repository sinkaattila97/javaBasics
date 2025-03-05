import entities.Person;

import java.util.Arrays;
import java.util.stream.Stream;

public class C_StreamsFromArrays {
    public static void main(String[] args) {
        Person p01 = new Person("Alice", 25);
        Person p02 = new Person("Bob", 30);
        Person p03 = new Person("Charlie", 35);
        Person p04 = new Person("David", 32);
        Person p05 = new Person("Robert", 33);

        Person[] people = {p01, p02, p03, p04, p05};

        //Create a stream of object from an array
        long count = Stream.of(people).count();
        System.out.println("Number of people: " + count);

        Arrays.stream(people).forEach(p -> System.out.println(p.getName()));

    }
}
