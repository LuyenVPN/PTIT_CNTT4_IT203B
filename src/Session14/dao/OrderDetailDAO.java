package Session14.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDetailDAO {

    public void insertBatch(int orderId, int productId, int quantity, Connection conn) throws Exception {
        String sql = "insert into order_details(order_id, product_id, quantity) values (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, orderId);
        ps.setInt(2, productId);
        ps.setInt(3, quantity);

        ps.addBatch();
        ps.executeBatch();
    }
}
