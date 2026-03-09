package Session2;

import java.util.Scanner;

public class B3_s2 {
    @FunctionalInterface
    interface Authenticatable {
        String getPassword();
        default boolean isAuthenticated() {
            return getPassword() != null && !getPassword().isBlank();
        }

        static String encrypt(String rawPassword) {
            if (rawPassword == null) {
                return null;
            }
            return Integer.toHexString(rawPassword.hashCode());
        }
    }
    public static void main(String[] args) {
        System.out.println("Mã hóa dữ liệu: ");
        System.out.println("Nhập vào dữ liệu cần mã hóa: ");
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();
        String encryptedPassword = Authenticatable.encrypt(password);
        System.out.println("Dữ liệu sau khi mã hóa: " + encryptedPassword);
    }
}
