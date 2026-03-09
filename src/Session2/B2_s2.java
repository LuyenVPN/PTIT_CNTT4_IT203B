package Session2;

import java.util.Scanner;

public class B2_s2 {
    @FunctionalInterface
    interface PasswordValidator {
        boolean validate(String password);
    }
    public static void main(String[] args) {
        PasswordValidator input = password -> password.length() >= 8;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mật khẩu của bạn: ");
        String password = sc.nextLine();
        System.out.println(" -> "+input.validate(password));

    }
}