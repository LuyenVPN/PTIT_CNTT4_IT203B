package MiniProjectSs5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidProductException {
        Scanner sc = new Scanner(System.in);
        ProductManager pm = new ProductManager();
        while (true) {
            System.out.println("===== Product Management system =====");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiện thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo id");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
            switch (choice) {
                case 1:

                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    Product p = new Product(id, name, price, quantity, category);
                    pm.addProduct(p);
                    System.out.println("Thêm sản phẩm thành công!");
                    break;

                case 2:
                    pm.displayProducts();
                    break;

                case 3:

                    break;

                case 4:
                    pm.deleteProduct();
                    break;

                case 5:
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    return;

            }
            } catch (InvalidProductException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}
