package MiniProjectSs14;

import java.sql.*;

public class TransferMoney {

    private static final String URL = "jdbc:mysql://localhost:3306/banking";
    private static final String USER = "root";
    private static final String PASS = "luyen123";

    public static void main(String[] args) {

        String fromAccount = "ACC01";
        String toAccount = "ACC02";
        double amount = 3000;

        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASS)
        ) {
            conn.setAutoCommit(false); // bắt đầu transaction

            // 1. kiểm tra số dư
            String checkSql = "select balance from accounts where accountid = ?";
            double balance = 0;

            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                ps.setString(1, fromAccount);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    balance = rs.getDouble("balance");
                } else {
                    throw new SQLException("Tài khoản không tồn tại!");
                }
            }

            if (balance < amount) {
                throw new SQLException("Không đủ tiền!");
            }

            // 2. gọi procedure trừ tiền
            try (CallableStatement cs = conn.prepareCall("{call sp_updatebalance(?, ?)}")) {
                cs.setString(1, fromAccount);
                cs.setDouble(2, -amount);
                cs.execute();
            }

            // 3. gọi procedure cộng tiền
            try (CallableStatement cs = conn.prepareCall("{call sp_updatebalance(?, ?)}")) {
                cs.setString(1, toAccount);
                cs.setDouble(2, amount);
                cs.execute();
            }

            // 4. commit nếu OK
            conn.commit();
            System.out.println("Chuyển tiền thành công!");

            // 5. hiển thị kết quả
            String resultSql = "select * from accounts where accountid in (?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(resultSql)) {
                ps.setString(1, fromAccount);
                ps.setString(2, toAccount);

                ResultSet rs = ps.executeQuery();

                System.out.println("----- KẾT QUẢ -----");
                while (rs.next()) {
                    System.out.println(
                            rs.getString("accountid") + " | " +
                                    rs.getString("fullname") + " | " +
                                    rs.getDouble("balance")
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}