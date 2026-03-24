package Session14.dao;


import Session14.utils.DBConnection;
import java.sql.*;

public class ReportDAO {

    public void getTopBuyers() throws Exception {
        Connection conn = DBConnection.getConnection();
        CallableStatement cs = conn.prepareCall("{call sp_get_top_buyers()}");

        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " - " + rs.getInt("total"));
        }
    }

    public void getRevenue() throws Exception {
        Connection conn = DBConnection.getConnection();
        CallableStatement cs = conn.prepareCall("{? = call func_total_revenue()}");

        cs.registerOutParameter(1, Types.DOUBLE);
        cs.execute();

        System.out.println("revenue: " + cs.getDouble(1));
    }
}
