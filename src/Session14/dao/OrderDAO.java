package Session14.dao;

import java.sql.*;

public class OrderDAO {

    public int createOrder(int userId, Connection conn) throws Exception {
        String sql = "insert into orders(user_id) values (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
}
