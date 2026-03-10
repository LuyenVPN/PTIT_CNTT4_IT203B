package Session3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class B4_s3 {
    record User(String username, String email) {}

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("bob", "bob@gmail.com"),
                new User("bob", "bob2@gmail.com"),
                new User("alice", "alice@gmail.com"),
                new User("alice", "alice2@gmail.com"),
                new User("charlie", "charlie@gmail.com")
        );

        // dùng set để loại bỏ trùng username
        Set<String> seen = new HashSet<>();
        users.stream()
                .filter(u -> seen.add(u.username()))
                .forEach(System.out::println);
        // collect to map để loại bỏ trùng username
        users.stream()
                .collect(Collectors.toMap(User::username, u -> u, (u1, u2)->u1))
                .values()
                .forEach(System.out::println);
    }
}
