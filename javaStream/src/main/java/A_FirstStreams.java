import entities.Person;

import java.util.List;

public class A_FirstStreams {

    public static void main(String[] args) {
        Person p01 = new Person("Alice", 25);
        Person p02 = new Person("Bob", 30);
        Person p03 = new Person("Charlie", 35);
        Person p04 = new Person("David", 40);
        Person p05 = new Person("", 45);
        Person p06 = new Person("Frank", 50);
        Person p07 = new Person("Grace", 55);
        Person p08 = new Person("Heidi", 60);
        Person p09 = new Person("Ivan", 65);

        List<Person> people = List.of(p01, p02, p03, p04, p05, p06, p07, p08, p09);

        long countEmptyNames =
                people.stream()
                        .map(p -> p.getName())
                        .filter(name -> name.isEmpty())
                        .count();

        long countNonEmptyNames =
                people.stream()
                        .map(p -> p.getName())
                        .filter(name -> !name.isEmpty())
                        .count();


        System.out.println("Empty names = " + countEmptyNames);
        System.out.println("Non empty names = " + countNonEmptyNames);

    }
}
