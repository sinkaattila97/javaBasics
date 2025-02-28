import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SomeMapping {

    public static void main(String[] args) {
        User sarah = new User("Sarah");
        User james = new User("James");
        User mary = new User("Mary");
        User john = new User("John");

        List<User> users = List.of(sarah, james, mary, john);
        List<String> names = new ArrayList<>();
        //a map takes a list of a type and returns a list of another type

        Function<User, String> userToName = (User user) -> user.getName();
        //I need to use this function to map the users to their names
        for (User user : users) {
            String name = userToName.apply(user);
            names.add(name);
        }

        //now to print it
        users.forEach(u -> System.out.println(u));
        //Simplified: users.forEach(System.out::println);
        names.forEach(u -> System.out.println(u));

    }
}
