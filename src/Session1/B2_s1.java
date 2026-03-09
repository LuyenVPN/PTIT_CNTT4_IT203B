package Session1;

import java.util.Scanner;

public class B2_s1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Nhập tổng số người dùng: ");
            int sum = sc.nextInt();

            System.out.print("Nhập số nhóm muốn chia: ");
            int a = sc.nextInt();

            int input = sum / a;

            System.out.println("Mỗi nhóm có: " + input + " người");
        }

        catch (ArithmeticException e) {
            System.out.println("Không thể chia cho 0!");
        }

        System.out.println("Chương trình vẫn tiếp tục chạy sau khi xử lý lỗi.");

        sc.close();
    }
}
