package Session1;

import java.util.Scanner;

public class B1_s1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập năm sinh của bạn: ");
            String input = sc.nextLine();

            int birthYear = Integer.parseInt(input);

            int currentYear = 2026;
            int age = currentYear - birthYear;

            System.out.println("Tuổi của bạn là: " + age);
        }

        catch (NumberFormatException e) {
            System.out.println("Lỗi: Bạn phải nhập năm sinh bằng số! Ví dụ: 2006");
        }

        finally {
            sc.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}
