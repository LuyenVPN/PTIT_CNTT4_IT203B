package Session12.Bai5;

import java.sql.*;
import java.util.Scanner;

public class HospitalManager {

    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "luyen123";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 1. Hiển thị danh sách bệnh nhân
    public static void showPatients() {
        String sql = "select * from patients";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("===== DANH SÁCH BỆNH NHÂN =====");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("department")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2. Thêm bệnh nhân (PreparedStatement chống SQL Injection)
    public static void addPatient(Scanner sc) {
        String sql = "insert into patients(name, age, department, disease, admission_date) values(?,?,?,?,curdate())";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Tên: ");
            String name = sc.nextLine();

            System.out.print("Tuổi: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Khoa: ");
            String dep = sc.nextLine();

            System.out.print("Bệnh lý: ");
            String disease = sc.nextLine();

            ps.setString(1, name); // xử lý được L'Oréal
            ps.setInt(2, age);
            ps.setString(3, dep);
            ps.setString(4, disease);

            ps.executeUpdate();
            System.out.println(">> Thêm thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. Cập nhật bệnh án
    public static void updateDisease(Scanner sc) {
        String sql = "update patients set disease = ? where id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Nhập ID bệnh nhân: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Bệnh lý mới: ");
            String disease = sc.nextLine();

            ps.setString(1, disease);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(">> Cập nhật thành công!");
            } else {
                System.out.println(">> Không tìm thấy!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Xuất viện + gọi Stored Procedure
    public static void dischargePatient(Scanner sc) {
        String call = "{call calculate_discharge_fee(?, ?)}";

        try (Connection conn = getConnection();
             CallableStatement cs = conn.prepareCall(call)) {

            System.out.print("Nhập ID bệnh nhân: ");
            int id = Integer.parseInt(sc.nextLine());

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.DOUBLE);

            cs.execute();

            double fee = cs.getDouble(2);

            System.out.println(">> Tổng viện phí: " + fee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Thêm bệnh nhân");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & tính phí");
            System.out.println("5. Thoát");

            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    showPatients();
                    break;
                case 2:
                    addPatient(sc);
                    break;
                case 3:
                    updateDisease(sc);
                    break;
                case 4:
                    dischargePatient(sc);
                    break;
                case 5:
                    System.out.println("Thoát...");
                    return;
                default:
                    System.out.println("Sai lựa chọn!");
            }
        }
    }
}