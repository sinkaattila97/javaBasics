import entities.UserTwo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UserComparator {
    public static void main(String[] args) {
        UserTwo sarah = new UserTwo("Sarah", 30);
        UserTwo james = new UserTwo("James", 25);
        UserTwo lila = new UserTwo("Lila", 28);
        UserTwo john = new UserTwo("John", 24);
        UserTwo john2 = new UserTwo("John2", 25);

        List<UserTwo> users = Arrays.asList(sarah, james, lila, john, john2);

        Comparator<UserTwo> cmpName = Comparator.comparing(user -> user.getName());
        users.sort(cmpName);
        users.forEach(u -> System.out.println(u));


        //-----------------------------------------------------------------------
        Comparator<UserTwo> cmpAge = Comparator.comparingInt(user -> user.getAge());
        Comparator<UserTwo> comparator = cmpName.thenComparing(cmpAge);

        Comparator<UserTwo> reversed = comparator.reversed();
        users.sort(reversed);

        users.sort(comparator);
        users.forEach(u -> System.out.println(u));



    }
}
