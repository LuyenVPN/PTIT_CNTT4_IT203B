package Session1;

import java.io.IOException;

public class B4_s1 {
    public static void saveToFile() throws IOException {
        System.out.println("Đang ghi dữ liệu vào file...");
        throw new IOException("Lỗi khi ghi file!");
    }

    public static void processUserData() throws IOException {
        System.out.println("Đang xử lý dữ liệu người dùng...");
        saveToFile();
    }

    public static void main(String[] args) {

        try {
            processUserData();
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }

        System.out.println("Chương trình vẫn tiếp tục chạy.");
    }
}
