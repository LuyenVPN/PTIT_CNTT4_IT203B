package Session2;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class B1_s2 {
    static class User {
        String username;
        String role;

        public User(String username, String role) {
            this.username = username;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }

        public String toString() {
            return username + " - " + role;
        }
    }

    public static void main(String[] args) {
        Predicate<User> isAdmin = user -> user.getRole().equals("ADMIN");
        Function<User, String> getUsername = user -> user.getUsername();
        Consumer<User> printUser = user -> System.out.println(user);
        Supplier<User> createUser = () -> new User("guest", "USER");
        Supplier<User> updateUser = () -> new User("luyen", "ADMIN");

        User newUser = createUser.get();
        System.out.println("Có phải là admin hay không " + isAdmin.test(newUser));
        System.out.println("Thông tin: " + getUsername.apply(newUser));
        printUser.accept(newUser);
        User updatedUser = updateUser.get();
        System.out.println("Có phải là admin hay không " + isAdmin.test(updatedUser));
        System.out.println("Thông tin: " + getUsername.apply(updatedUser));
        printUser.accept(updatedUser);


    }
}
