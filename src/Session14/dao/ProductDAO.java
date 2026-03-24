package Session14.dao;

import Session14.utils.DBConnection;
import java.sql.*;

public class ProductDAO {

    public int getStockForUpdate(int productId, Connection conn) throws Exception {
        String sql = "select stock from products where product_id = ? for update";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, productId);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("stock");
        }
        return 0;
    }

    public void updateStock(int productId, int newStock, Connection conn) throws Exception {
        String sql = "update products set stock = ? where product_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, newStock);
        ps.setInt(2, productId);
        ps.executeUpdate();
    }
}
