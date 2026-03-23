package Session12.Bai4;

import Session12.Bai1.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

class TestResult {
    String data;

    public TestResult(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

public class Main {
    public static void main(String[] args) {
        DB db = new DB();
        Connection conn = db.openConnection();

        try {
            List<TestResult> list = new ArrayList<>();
            list.add(new TestResult("A+"));
            list.add(new TestResult("B+"));
            list.add(new TestResult("O-"));

            String sql = "insert into Results(data) values(?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (TestResult tr : list) {
                ps.setString(1, tr.getData());
                ps.executeUpdate();
            }

            System.out.println("Insert thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//Phần 1 – Phân tích
// Vấn đề của code hiện tại
//for (TestResult tr : list) {
//String sql = "INSERT INTO Results(data) VALUES('" + tr.getData() + "')";
//Statement stmt = conn.createStatement();
//    stmt.executeUpdate(sql);
//}
//
// => Với 1000 bản ghi, DB phải làm 1000 lần:
//
//Parse (phân tích cú pháp SQL)
//Validate (kiểm tra bảng, cột)
//Optimize (tạo execution plan)
//Execute (thực thi)
// Lãng phí ở đâu?
//Mỗi lần lặp:
//Tạo Statement mới
//SQL khác nhau (do nối chuỗi)
//DB không cache được execution plan
//
//=> Kết quả:
//
//CPU DB bị tốn nhiều
//Tăng thời gian xử lý
//Gây chậm hệ thống (đặc biệt khi nhiều user)
//PreparedStatement giải quyết thế nào?
//INSERT INTO Results(data) VALUES(?)
//
//  → DB:
//Parse 1 lần
//Cache execution plan
//Chỉ thay đổi dữ liệu mỗi lần