package Session7;

import java.util.*;

public class B1_s7 {
    static class Product {
        private String id;
        private String name;
        private double price;

        public Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
            System.out.println("Tạo sản phẩm: " + id + " - " + name + " - " + price);
        }

        public double getPrice() {
            return price;
        }

        public String getId() {
            return id;
        }
    }

    static class Customer {
        private String name;
        private String email;
        private String address;

        public Customer(String name, String email, String address) {
            this.name = name;
            this.email = email;
            this.address = address;
            System.out.println("Tạo khách hàng: " + name + " - " + email);
        }

        public String getEmail() {
            return email;
        }
    }

    static class OrderItem {
        Product product;
        int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }

    static class Order {
        private String orderId;
        private Customer customer;
        private List<OrderItem> items = new ArrayList<>();
        private double total;

        public Order(String orderId, Customer customer) {
            this.orderId = orderId;
            this.customer = customer;
            System.out.println("Đơn hàng " + orderId + " được tạo");
        }

        public void addProduct(Product product, int quantity) {
            items.add(new OrderItem(product, quantity));
            System.out.println("Đã thêm sản phẩm " + product.getId());
        }

        public List<OrderItem> getItems() {
            return items;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public double getTotal() {
            return total;
        }

        public String getOrderId() {
            return orderId;
        }

        public Customer getCustomer() {
            return customer;
        }
    }

    static class OrderCalculator {

        public double calculateTotal(Order order) {
            double total = 0;

            for (OrderItem item : order.getItems()) {
                total += item.product.getPrice() * item.quantity;
            }

            return total;
        }
    }

    static class OrderRepository {

        private List<Order> orders = new ArrayList<>();

        public void save(Order order) {
            orders.add(order);
            System.out.println("Đã lưu đơn hàng " + order.getOrderId());
        }
    }

    static class EmailService {

        public void sendConfirmation(Customer customer, Order order) {
            System.out.println("Đã gửi email đến " + customer.getEmail() +
                    ": Đơn hàng " + order.getOrderId() + " đã được tạo");
        }
    }
    public static void main(String[] args) {

        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);

        Customer customer = new Customer("Nguyễn Văn A", "a@example.com", "Hà Nội");

        Order order = new Order("ORD001", customer);

        order.addProduct(p1, 1);
        order.addProduct(p2, 2);

        System.out.println("Tính tổng tiền");

        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculateTotal(order);
        order.setTotal(total);

        System.out.println("Tổng tiền: " + total);

        System.out.println("Lưu đơn hàng");

        OrderRepository repo = new OrderRepository();
        repo.save(order);

        System.out.println("Gửi email xác nhận");

        EmailService emailService = new EmailService();
        emailService.sendConfirmation(customer, order);
    }
}
