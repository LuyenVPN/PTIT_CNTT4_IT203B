package Session11.Bai4;

public class Bai4 {
//    public static void main(String[] args) {
//        String patientName = inputName;
//
//// loại bỏ ký tự nguy hiểm
//        patientName = patientName.replace("'", "");
//        patientName = patientName.replace("--", "");
//        patientName = patientName.replace(";", "");
//
//        // tạo câu SQL an toàn hơn
//        String sql = "SELECT * FROM Patients WHERE full_name = '" + patientName + "'";
//
//        ResultSet rs = stmt.executeQuery(sql);
//
//        while (rs.next()) {
//            System.out.println(rs.getString("full_name"));
//        }
//    }
}
//Phần 1 – Phân tích
//
//Đoạn code bị lỗi:

//String patientName = "' OR '1'='1";
//String sql = "SELECT * FROM Patients WHERE full_name = '" + patientName + "'";
//
// => Sau khi nối chuỗi, câu SQL thực tế trở thành:
//
//SELECT * FROM Patients WHERE full_name = '' OR '1'='1'
//full_name = '' → thường là false
//        '1'='1' → luôn true
//
//        => Vì có OR, nên:
//
//        false OR true → true
//        ⇒ mệnh đề WHERE luôn đúng với mọi bản ghi
//
// => Kết quả: trả về toàn bộ bảng Patients → rò rỉ dữ liệu (SQL Injection)