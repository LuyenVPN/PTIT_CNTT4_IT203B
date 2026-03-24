package Session13.Bai4;

import java.sql.*;
import java.util.*;

class BenhNhanDTO {
    String maBenhNhan;
    String ten;
    List<DichVu> dsDichVu = new ArrayList<>();
}

class DichVu {
    String tenDichVu;
    String thoiDiem;
}

public class DashboardDAO {

    public List<BenhNhanDTO> getDanhSachBenhNhan(Connection conn) throws SQLException {

        String sql = """
            select 
                bn.maBenhNhan,
                bn.ten,
                dv.tenDichVu,
                dv.thoiDiem
            from benhnhan bn
            left join dichvusudung dv 
                on bn.maBenhNhan = dv.maBenhNhan
            order by bn.maBenhNhan
        """;

        Map<String, BenhNhanDTO> map = new LinkedHashMap<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String ma = rs.getString("maBenhNhan");

                // Nếu chưa có thì tạo mới
                BenhNhanDTO bn = map.get(ma);
                if (bn == null) {
                    bn = new BenhNhanDTO();
                    bn.maBenhNhan = ma;
                    bn.ten = rs.getString("ten");
                    map.put(ma, bn);
                }

                // ===== XỬ LÝ BẪY 2 Ở ĐÂY =====
                // Nếu bệnh nhân chưa có dịch vụ thì các cột dv sẽ null
                String tenDV = rs.getString("tenDichVu");

                if (tenDV != null) {
                    DichVu dv = new DichVu();
                    dv.tenDichVu = tenDV;
                    dv.thoiDiem = rs.getString("thoiDiem");

                    bn.dsDichVu.add(dv);
                }
                // => nếu null thì bỏ qua → vẫn giữ bn nhưng list rỗng
            }
        }

        return new ArrayList<>(map.values());
    }
}