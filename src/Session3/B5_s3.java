package Session3;
import java.util.*;


public class B5_s3 {
    public static void main(String[] args) {
        record User(String username, String email) {}

        List<User> users = List.of(
                new User("alexander", "a@gmail.com"),
                new User("bob", "b@gmail.com"),
                new User("charlotte", "c@gmail.com"),
                new User("Benjamin", "d@gmail.com"),
                new User("anna", "e@gmail.com")
        );

        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                .limit(3)
//                .map(User::username)
                .forEach(u->System.out.println(u.username()));
    }
}