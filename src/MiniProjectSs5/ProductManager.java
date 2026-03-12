package MiniProjectSs5;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) throws InvalidProductException {
        if (p.getId() < 0) {
            throw new InvalidProductException("ID không được âm");
        }

        if (p.getPrice() < 0) {
            throw new InvalidProductException("Giá không được âm");
        }

        if (p.getQuantity() < 0) {
            throw new InvalidProductException("Số lượng không được âm");
        }
        for (Product listproduct : products) {
            if (listproduct.getId() == p.getId()) {
                throw new InvalidProductException("ID đã tồn tại!");
            }
        }
        products.add(p);
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-10s %-15s\n", "ID", "NAME", "PRICE", "QUANTITY", "CATEGORY");
        System.out.println("--------------------------------------------------------------------------");
        NumberFormat nf = NumberFormat.getNumberInstance();
        for (Product p : products) {
            System.out.printf("%-5d %-15s %-15s %-10d %-15s\n",
                    p.getId(),
                    p.getName(),
                    nf.format(p.getPrice()),
                    p.getQuantity(),
                    p.getCategory());
        }

        System.out.println("--------------------------------------------------------------------------");
    }

    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {

    }

    public void deleteProduct() {
        if(products.size()>0){
            products = products.stream()
                    .filter(p -> p.getQuantity() > 0)
                    .toList();
            System.out.println("Đã xóa sản phẩm đã hết hàng!");
        }else {
            System.out.println("Danh sách sản phẩm trống!");
        }
    }
}
