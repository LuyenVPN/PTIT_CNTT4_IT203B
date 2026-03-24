package Session13.Bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void capPhatThuoc(int medicineId, int patientId) {
        Connection conn = null;
        try {
            conn = DataConnect.openConnection(); // mở kết nối tới database
            conn.setAutoCommit(false); // tắt auto commit để bắt đầu transaction

            String sqlUpdateInventory = "update Medicine_Inventory set quantity = quantity - 1 where medicine_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlUpdateInventory);
            ps1.setInt(1, medicineId); // truyền id thuốc
            ps1.executeUpdate(); // trừ thuốc trong kho (chưa commit)

            int x = 10 / 0; // giả lập lỗi → để test rollback

            String sqlInsertHistory = "insert into Prescription_History (patient_id, medicine_id, date) values (?, ?, now())";
            PreparedStatement ps2 = conn.prepareStatement(sqlInsertHistory);
            ps2.setInt(1, patientId); // truyền id bệnh nhân
            ps2.setInt(2, medicineId); // truyền id thuốc
            ps2.executeUpdate(); // lưu lịch sử cấp phát

            conn.commit(); // nếu tất cả OK → lưu xuống DB
            System.out.println("Cap phat thuoc thanh cong");

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // có lỗi → hoàn tác toàn bộ (không trừ thuốc)
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Co loi xay ra: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close(); // đóng kết nối tránh leak tài nguyên
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        capPhatThuoc(1, 101); // gọi hàm test
    }
}

//Phần 1 – Vì sao bị lỗi nhưng vẫn trừ thuốc?

//Nguyên nhân nằm ở Auto-Commit mặc định của JDBC = true
//Cụ thể luồng chạy:
//        ps1.executeUpdate() (trừ thuốc)
//        → chạy xong là DB tự commit ngay lập tức

//Sau đó đến dòng:
//int x = 10 / 0;
//→ lỗi Runtime xảy ra

//Vì câu lệnh đầu đã commit rồi, nên:
//Không thể rollback lại
//Dữ liệu đã bị ghi vĩnh viễn
//Câu lệnh thứ 2 (insert history) chưa chạy → không có log

//=> Kết quả:
//Kho bị trừ
//Không có lịch sử

// => Vi phạm Atomicity (tính nguyên tử) của Transaction
