package forLoopToStream;

import entities.Person;

import java.util.List;
import java.util.stream.Stream;

public class A_AverageAge {
    public static void main(String[] args) {
        Person p01 = new Person("Paul", 25);
        Person p02 = new Person("Bob", 30);
        Person p03 = new Person("Charlie", 35);
        Person p04 = new Person("David", 40);
        Person p05 = new Person("Peter", 45);

        List<Person> people = List.of(p01, p02, p03, p04, p05);

        int sum = 0;
        int count = 0;

        double average = people.stream()
                .mapToInt(p -> p.getAge())
                .filter(age -> age > 20)
                .average()
                .orElseThrow();

        System.out.println("Average: " + average);
    }
}
