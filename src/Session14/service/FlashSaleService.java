package Session14.service;

import Session14.dao.*;
import Session14.utils.DBConnection;

import java.sql.Connection;

public class FlashSaleService {

    ProductDAO productDAO = new ProductDAO();
    OrderDAO orderDAO = new OrderDAO();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    public void placeOrder(int userId, int productId, int quantity) {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();

            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // lock row
            int stock = productDAO.getStockForUpdate(productId, conn);

            if (stock < quantity) {
                throw new RuntimeException("het hang");
            }

            // update stock
            productDAO.updateStock(productId, stock - quantity, conn);

            // create order
            int orderId = orderDAO.createOrder(userId, conn);

            // insert detail (batch)
            orderDetailDAO.insertBatch(orderId, productId, quantity, conn);

            conn.commit();
            System.out.println("dat hang thanh cong");

        } catch (Exception e) {
            try {
                conn.rollback();
                System.out.println("rollback: " + e.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
