package Session12.Bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DB db = new DB();
        Connection conn = db.openConnection();

        try {
            System.out.print("Nhập ID bệnh nhân: ");
            int patientId = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập nhiệt độ: ");
            double temp = Double.parseDouble(sc.nextLine());

            System.out.print("Nhập nhịp tim: ");
            int heartRate = Integer.parseInt(sc.nextLine());

            String sql = "update Vitals set temperature = ?, heart_rate = ? where p_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, temp);
            ps.setInt(2, heartRate);
            ps.setInt(3, patientId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//Phần 1 – Phân tích
// Vấn đề khi dùng Statement (nối chuỗi)
//
//Ví dụ:
//
//String sql = "UPDATE Vitals SET temperature = " + temp + " WHERE p_id = " + patientId;
//
//Nếu máy dùng locale kiểu Pháp/Việt:
//
//        37,5
//
//        → SQL sẽ thành:
//
//UPDATE Vitals SET temperature = 37,5 WHERE p_id = 1
//
// => Sai cú pháp SQL (DB chỉ hiểu dấu chấm ., không hiểu dấu phẩy ,)
//→ Gây lỗi ngay lập tức
//
// Vì sao setDouble() và setInt() giải quyết được?
//
//        1. Không nối chuỗi → không phụ thuộc định dạng hệ điều hành
//
//ps.setDouble(1, temp);
//ps.setInt(2, heartRate);
//
//→ Java truyền dữ liệu dưới dạng nhị phân (binary) tới DB
//→ KHÔNG convert sang String theo locale
//
//2. Driver JDBC tự xử lý chuẩn SQL
//
//JDBC driver sẽ đảm bảo:
//double → đúng format chuẩn DB (37.5)
//int → đúng kiểu số
//Không bị ảnh hưởng bởi:
//Region (VN, FR,…)
//Dấu , hay .
