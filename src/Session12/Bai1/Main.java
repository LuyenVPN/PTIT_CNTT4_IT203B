package Session12.Bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DB db = new DB();
        Connection conn = db.openConnection();

        try {
            System.out.print("Nhập mã bác sĩ: ");
            String code = sc.nextLine();

            System.out.print("Nhập mật khẩu: ");
            String pass = sc.nextLine();

            String sql = "select * from Doctors where code = ? and pass = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, code);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công!");
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//Phần 1 – Phân tích
//
//Vì sao PreparedStatement chống được SQL Injection?
//
//Vấn đề của đoạn code cũ là nối chuỗi trực tiếp:
//
//SELECT * FROM Doctors WHERE code = 'abc' AND pass = '' OR '1'='1'
//
//        → DB hiểu toàn bộ là câu lệnh SQL hoàn chỉnh, nên điều kiện '1'='1' luôn đúng → bypass login.
//
//Cơ chế của PreparedStatement
//
//PreparedStatement hoạt động theo 2 bước:
//
//        1. Pre-compiled (biên dịch trước)
//Câu SQL được gửi lên DB trước, với dạng:
//
//SELECT * FROM Doctors WHERE code = ? AND pass = ?
//
//        → DB biên dịch cấu trúc SQL cố định, chưa có dữ liệu người dùng.
//
//2. Binding tham số (gán giá trị sau)
//Giá trị user nhập được truyền riêng:
//
//        ps.setString(1, code);
//ps.setString(2, pass);
//
//→ DB không coi dữ liệu là SQL, chỉ coi là giá trị thuần (data)
//
//Vì sao an toàn?
//
//Nếu user nhập:
//
//        ' OR '1'='1
//
//        → DB sẽ hiểu:
//
//pass = "' OR '1'='1"
//
//        → Đây chỉ là chuỗi bình thường, KHÔNG phải điều kiện SQL
//→ Không thể phá vỡ câu lệnh → Injection thất bại
