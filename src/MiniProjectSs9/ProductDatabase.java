package MiniProjectSs9;

import java.util.*;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getAll() {
        return products;
    }

    public Product findById(String id) {
        for (Product p : products) {
            if (p.id.equals(id)) return p;
        }
        return null;
    }

}
