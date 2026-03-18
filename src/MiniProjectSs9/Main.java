package MiniProjectSs9;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("=========== Quản Lý Sản Phẩm ===========");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Thoát");
            System.out.println("========================================");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    int type;
                    while (true) {
                        System.out.print("Nhập loại sản phẩm (1 Physical, 2 Digital): ");

                        if (!sc.hasNextInt()) {
                            System.out.println("Vui lòng nhập số 1 hoặc 2.");
                            sc.nextLine();
                            continue;
                        }

                        type = sc.nextInt();
                        sc.nextLine();
                        if (type == 1 || type == 2) {
                            break;
                        }
                        System.out.println("Chỉ được nhập 1 hoặc 2.");
                    }


                    System.out.print("ID: ");
                    String id = sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print(type == 1 ? "Weight: " : "Size: ");
                    double extra = sc.nextDouble();

                    Product p = ProductFactory.createProduct(type, id, name, price, extra);
                    db.addProduct(p);
                    break;

                case 2:
                    for (Product prod : db.getAll()) {
                        prod.displayInfo();
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID cần cập nhật: ");
                    String uid = sc.nextLine();

                    Product up = db.findById(uid);
                    if (up != null) {
                        System.out.print("Nhập tên sản phẩm mới: ");
                        up.name = sc.nextLine();
                    }else {
                        System.out.println("Không tìm thấy sản phẩm với ID: " + uid);
                    }
                    break;

                case 4:
                    break;

                case 5:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    return;
            }
        }
    }
}
