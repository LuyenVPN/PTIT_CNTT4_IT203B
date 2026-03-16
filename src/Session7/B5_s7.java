package Session7;
import java.util.*;

public class B5_s7 {
    static class Product {
        String id;
        String name;
        double price;
        String category;

        public Product(String id, String name, double price, String category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.category = category;
        }
    }

    static class Customer {
        String name;
        String email;
        String phone;

        public Customer(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
        }
    }

    static class OrderItem {
        Product product;
        int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public double getTotal() {
            return product.price * quantity;
        }
    }

    static class Order {
        String id;
        Customer customer;
        List<OrderItem> items = new ArrayList<>();
        double total;
        double finalAmount;

        public Order(String id, Customer customer) {
            this.id = id;
            this.customer = customer;
        }
    }


    interface DiscountStrategy {
        double apply(double total);
    }

    static class PercentageDiscount implements DiscountStrategy {

        private double percent;

        public PercentageDiscount(double percent) {
            this.percent = percent;
        }

        public double apply(double total) {
            return total - total * percent / 100;
        }
    }

    static class FixedDiscount implements DiscountStrategy {

        private double amount;

        public FixedDiscount(double amount) {
            this.amount = amount;
        }

        public double apply(double total) {
            return total - amount;
        }
    }

    class HolidayDiscount implements DiscountStrategy {

        public double apply(double total) {
            return total * 0.85;
        }
    }


    interface PaymentMethod {
        void pay(double amount);
    }

    class CODPayment implements PaymentMethod {
        public void pay(double amount) {
            System.out.println("Thanh toán COD: " + amount);
        }
    }

    static class CreditCardPayment implements PaymentMethod {
        public void pay(double amount) {
            System.out.println("Thanh toán thẻ tín dụng: " + amount);
        }
    }

    class MomoPayment implements PaymentMethod {
        public void pay(double amount) {
            System.out.println("Thanh toán MoMo: " + amount);
        }
    }

    class VNPayPayment implements PaymentMethod {
        public void pay(double amount) {
            System.out.println("Thanh toán VNPay: " + amount);
        }
    }


    interface OrderRepository {
        void save(Order order);
        List<Order> findAll();
    }

    static class FileOrderRepository implements OrderRepository {

        List<Order> orders = new ArrayList<>();

        public void save(Order order) {
            orders.add(order);
            System.out.println("Đã lưu đơn hàng " + order.id);
        }

        public List<Order> findAll() {
            return orders;
        }
    }

    static class DatabaseOrderRepository implements OrderRepository {

        List<Order> orders = new ArrayList<>();

        public void save(Order order) {
            orders.add(order);
            System.out.println("Lưu đơn hàng vào database: " + order.id);
        }

        public List<Order> findAll() {
            return orders;
        }
    }


    interface NotificationService {
        void send(String message, String recipient);
    }

    static class EmailNotification implements NotificationService {
        public void send(String message, String recipient) {
            System.out.println("Đã gửi email xác nhận");
        }
    }

    static class SMSNotification implements NotificationService {
        public void send(String message, String recipient) {
            System.out.println("Gửi SMS: " + message);
        }
    }


    static class InvoiceGenerator {

        public void generate(Order order, double discount) {

            System.out.println("=== HÓA ĐƠN ===");

            System.out.println("Khách: " + order.customer.name);

            for (OrderItem item : order.items) {

                System.out.println(
                        item.product.name +
                                " - Số lượng: " + item.quantity +
                                " - Đơn giá: " + item.product.price +
                                " - Thành tiền: " + item.getTotal()
                );
            }

            System.out.println("Tổng tiền: " + order.total);
            System.out.println("Giảm giá: " + discount);
            System.out.println("Cần thanh toán: " + order.finalAmount);
        }
    }


    static class OrderService {

        private OrderRepository repository;
        private NotificationService notification;

        public OrderService(OrderRepository repository,
                            NotificationService notification) {
            this.repository = repository;
            this.notification = notification;
        }

        public void processOrder(Order order,
                                 DiscountStrategy discount,
                                 PaymentMethod payment) {

            double total = 0;

            for (OrderItem item : order.items) {
                total += item.getTotal();
            }

            order.total = total;

            double finalAmount = discount.apply(total);
            order.finalAmount = finalAmount;

            InvoiceGenerator invoice = new InvoiceGenerator();
            invoice.generate(order, total - finalAmount);

            payment.pay(finalAmount);

            repository.save(order);

            notification.send("Đơn hàng đã tạo", order.customer.email);
        }

        public List<Order> getOrders() {
            return repository.findAll();
        }
    }

        static List<Product> products = new ArrayList<>();
        static List<Customer> customers = new ArrayList<>();
        static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        OrderRepository repo = new FileOrderRepository();
        NotificationService notify = new EmailNotification();

        OrderService service = new OrderService(repo, notify);

        while (true) {

            System.out.println("\n1. Thêm sản phẩm");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Xem đơn hàng");
            System.out.println("5. Tính doanh thu");
            System.out.println("0. Thoát");
            System.out.println("Chọn chức năng: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();

                    System.out.print("Mã: ");
                    String id = sc.nextLine();

                    System.out.print("Tên: ");
                    String name = sc.nextLine();

                    System.out.print("Giá: ");
                    double price = sc.nextDouble();

                    sc.nextLine();
                    System.out.print("Danh mục: ");
                    String cat = sc.nextLine();

                    products.add(new Product(id, name, price, cat));

                    System.out.println("Đã thêm sản phẩm " + id);
                    break;

                case 2:
                    sc.nextLine();

                    System.out.print("Tên: ");
                    String cname = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    customers.add(new Customer(cname, email, phone));

                    System.out.println("Đã thêm khách hàng");
                    break;

                case 3:

                    Customer c = customers.get(0);

                    Order order = new Order("ORD001", c);

                    Product p = products.get(0);

                    order.items.add(new OrderItem(p, 1));

                    DiscountStrategy discount = new PercentageDiscount(10);

                    PaymentMethod payment = new CreditCardPayment();

                    service.processOrder(order, discount, payment);

                    break;

                case 4:

                    for (Order o : service.getOrders()) {
                        System.out.println(o.id + " - " + o.customer.name + " - " + o.finalAmount);
                    }

                    break;

                case 5:

                    double revenue = 0;

                    for (Order o : service.getOrders()) {
                        revenue += o.finalAmount;
                    }

                    System.out.println("Tổng doanh thu: " + revenue);

                    break;

                case 0:
                    return;
            }
        }
    }
}
