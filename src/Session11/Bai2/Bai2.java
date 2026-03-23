package Session11.Bai2;

public class Bai2 {
//    public static void main(String[] args) {
//        ResultSet rs = stmt.executeQuery("SELECT medicine_name, stock FROM Pharmacy");
//
//        while (rs.next()) {
//            try {
//                System.out.println("Thuốc: " + rs.getString("medicine_name") + " | Tồn kho: " + rs.getInt("stock"));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}

//Phần 1 – Phân tích
//
//if (rs.next()) chỉ kiểm tra một lần duy nhất xem có bản ghi hay không.
//Khi gọi rs.next():
//Lần đầu: con trỏ di chuyển từ trước dòng đầu tiên → dòng 1 (nếu có dữ liệu) → trả về true.
//Sau đó bạn không lặp lại, nên chỉ in 1 dòng rồi dừng.
//Nếu bảng rỗng:
//        rs.next() trả về false → không vào if → có thể gây lỗi logic (không xử lý trường hợp rỗng).