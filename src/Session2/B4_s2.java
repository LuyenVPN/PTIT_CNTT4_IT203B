package Session2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class B4_s2 {
    static class User {
        private String username;

        public User() {
            this.username = "guest";
        }

        public User(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("luyen"));
        users.add(new User("admin"));
        users.add(new User("guest"));

        // (user) -> user.getUsername()
        Function<User, String> getUsername = User::getUsername;

        // (s) -> System.out.println(s)
        Consumer<String> print = System.out::println;

        // () -> new User()
        Supplier<User> createUser = User::new;

        // áp dụng vào danh sách users
        for(User u : users){
            print.accept(getUsername.apply(u));
        }

        User newUser = createUser.get();
        print.accept(newUser.getUsername());
    }
}
