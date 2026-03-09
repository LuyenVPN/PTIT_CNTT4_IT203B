package Session1;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.Scanner;

public class B6_s1 {
    static class InvalidAgeException extends Exception {

        public InvalidAgeException(String msg) {
            super(msg);
        }

    }

    static class Logger {
        public static void logError(Exception e) {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String time = LocalDateTime.now().format(formatter);

            System.out.println("[ERROR] " + time + " - " + e.getMessage());
        }

    }

    static class User {

        private String name;
        private int age;

        public void setName(String name) {

            if (name != null && !name.trim().isEmpty()) {
                this.name = name;
            } else {
                System.out.println("Tên người dùng không hợp lệ.");
            }

        }

        public void setAge(int age) throws InvalidAgeException {

            if (age < 0) {
                throw new InvalidAgeException("Tuổi không thể âm!");
            }

            this.age = age;
        }

        public void printUser() {

            if (name != null) {
                System.out.println("Tên: " + name);
            }

            System.out.println("Tuổi: " + age);
        }

    }

    static class FileService {

        public static void saveToFile(User user) throws IOException {

            System.out.println("Đang lưu dữ liệu người dùng...");
            throw new IOException("Không thể ghi file!");

        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        try {
            System.out.print("Nhập tên: ");
            String name = sc.nextLine();
            user.setName(name);
            System.out.print("Nhập năm sinh: ");
            String input = sc.nextLine();
            int birthYear = Integer.parseInt(input);

            int age = 2026 - birthYear;

            user.setAge(age);

            user.printUser();

            FileService.saveToFile(user);

        }

        catch (NumberFormatException e) {
            Logger.logError(e);
        }

        catch (InvalidAgeException e) {
            Logger.logError(e);
        }

        catch (ArithmeticException e) {
            Logger.logError(e);
        }

        catch (IOException e) {
            Logger.logError(e);
        }

        finally {
            sc.close();
            System.out.println("Dọn dẹp tài nguyên...");
        }

    }

}
