package Session3;

import java.util.List;
import java.util.Optional;

public class B3_s3 {
    record User(String username, String email) {}
    public static void main(String[] args) {
        class UserRepository {

            List<User> users = List.of(
                    new User("alice", "alice@gmail.com"),
                    new User("bob", "bob@yahoo.com"),
                    new User("charlie", "charlie@gmail.com")
            );

            public Optional<User> findUserByUsername(String username) {
                return users.stream()
                        .filter(u -> u.username().equals(username))
                        .findFirst();
            }
        }

        UserRepository repo = new UserRepository();

        Optional<User> user = repo.findUserByUsername("alice");

        if (user.isPresent()) {
            System.out.println("Welcome " + user.get().username());
        } else {
            System.out.println("Guest login");
        }
    }
}
