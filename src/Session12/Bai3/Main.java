package Session12.Bai3;

import Session12.Bai1.DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DB db = new DB();
        Connection conn = db.openConnection();

        try {
            System.out.print("Nhập surgery_id: ");
            int surgeryId = Integer.parseInt(sc.nextLine());

            CallableStatement cstmt = conn.prepareCall("{call GET_SURGERY_FEE(?, ?)}");

            cstmt.setInt(1, surgeryId);

            // đăng ký OUT parameter
            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            double cost = cstmt.getDouble(2);

            System.out.println("Chi phí phẫu thuật: " + cost);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//Phần 1 – Phân tích
// Vì sao bị lỗi?
//
//Code lỗi của bạn:
//
//        cstmt.execute();
//double cost = cstmt.getDouble(2);
//
//=> Lỗi vì:
//
//JDBC không biết tham số thứ 2 là OUT
//Bạn chưa đăng ký OUT parameter
//
//→ Khi gọi getDouble(2):
//
//JDBC báo: "column index is out of range"
//Hoặc trả về sai giá trị
// Vì sao phải registerOutParameter() trước?
//
//CallableStatement cần biết:
//
//Tham số nào là IN
//Tham số nào là OUT
//Kiểu dữ liệu của OUT là gì
//
//=> Khi bạn gọi:
//
//        cstmt.registerOutParameter(2, Types.DOUBLE);
//
//→ JDBC sẽ:
//
//Cấp vùng nhớ nhận dữ liệu trả về
//Map đúng kiểu dữ liệu từ DB → Java
// Nếu SQL dùng DECIMAL thì sao?
//
//Trong SQL:
//
//DECIMAL / NUMERIC
//
//=> Trong Java phải dùng:
//
//Types.DECIMAL