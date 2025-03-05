import entities.City;
import entities.Person;

import java.util.List;

public class B_FlatMapping {
    public static void main(String[] args) {
        Person p01 = new Person("Alice", 25);
        Person p02 = new Person("Bob", 30);
        Person p03 = new Person("Charlie", 35);
        Person p04 = new Person("David", 32);
        Person p05 = new Person("Robert", 33);
        Person p06 = new Person("Frank", 26);
        Person p07 = new Person("Grace", 28);
        Person p08 = new Person("Heidi", 75);
        Person p09 = new Person("Ivan", 98);

        City newYork = new City("New York", p01, p02, p03);
        City shanghai = new City("Shanghai", p04, p05, p08);
        City berlin = new City("Berlin", p06, p07, p09);

        List<City> cities = List.of(newYork, shanghai, berlin);

        long count = cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .count();
        System.out.println("Total number of people in all cities: " + count);

        System.out.println("\n====================================\n");

        cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .forEach(p -> System.out.println(p.getName() + " (" + p.getAge() + ")"));

        System.out.println("\n====================================\n");

        cities.stream()
                .flatMap(city -> city.getPeople().stream())
                .map(Person::getName)
                .forEach(System.out::println);
    }
}
