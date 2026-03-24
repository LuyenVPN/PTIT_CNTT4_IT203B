package Session13.Bai2;

import Session13.Bai1.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void thanhToanVienPhi(int patientId, double amount, int invoiceId) {
        Connection conn = null;
        try {
            conn = DataConnect.openConnection(); // mở kết nối DB
            conn.setAutoCommit(false); // tắt auto commit → bắt đầu transaction

            String sqlUpdateWallet = "update Patient_Wallet set balance = balance - ? where patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlUpdateWallet);
            ps1.setDouble(1, amount); // số tiền cần trừ
            ps1.setInt(2, patientId); // id bệnh nhân
            ps1.executeUpdate(); // trừ tiền (chưa commit)

            String sqlUpdateInvoice = "update Invoices set status = 'PAID' where id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sqlUpdateInvoice);
            ps2.setInt(1, invoiceId); // id hóa đơn
            ps2.executeUpdate(); // cập nhật trạng thái hóa đơn

            conn.commit(); // cả 2 bước OK → lưu xuống DB
            System.out.println("Thanh toan thanh cong");

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // lỗi → hoàn tác (trả lại tiền)
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Co loi xay ra: " + e.getMessage()); // chỉ in lỗi là chưa đủ, rollback mới quan trọng
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true); // trả connection về trạng thái ban đầu
                if (conn != null) conn.close(); // đóng connection tránh bị treo / leak tài nguyên
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        thanhToanVienPhi(1, 500000, 1); // test
    }
}

// Phần 1 – Phân tích lỗi
//
//Bạn đang:
//setAutoCommit(false) → đã bắt đầu Transaction
//Có commit() ở cuối

//Nhưng trong catch chỉ:
//        System.out.println(...)
//
//=> Vấn đề nghiêm trọng:

//Khi lỗi xảy ra (ví dụ SQL sai):
//Transaction chưa commit
//Nhưng cũng không rollback
//Connection giữ trạng thái:
//Transaction đang mở (dang dở)
//Lock dữ liệu có thể vẫn giữ
//Gây treo connection / leak tài nguyên

//=> Vi phạm nguyên tắc Transaction:
//Không đảm bảo Atomicity
//Không đưa DB về trạng thái consistent

//
//=> BẮT BUỘC phải có:
//
//        conn.rollback();