package Session11.Bai3;

public class Bai3 {
//    public static void main(String[] args) {
//        String sql = "UPDATE Beds SET bed_status = 'Occupied' WHERE bed_id = " + inputId;
//
//        int rowsAffected = stmt.executeUpdate(sql);
//
//        if (rowsAffected > 0) {
//            System.out.println("Đã cập nhật giường bệnh thành công!");
//        } else {
//            System.out.println("Lỗi: Mã giường không tồn tại!");
//        }
//    }
}
//    Phần 1 – Phân tích
//
//    executeUpdate(sql) trả về số dòng (row) bị ảnh hưởng bởi câu lệnh SQL.
//> 0: có bản ghi được cập nhật (thành công)
//= 0: không có bản ghi nào bị ảnh hưởng → thường là do bed_id không tồn tại
//
// => Vì vậy:
//
//    Nếu executeUpdate() trả về 0 → có thể kết luận: mã giường không tồn tại → cần báo lỗi cho y tá
//    Nếu > 0 → cập nhật thành công