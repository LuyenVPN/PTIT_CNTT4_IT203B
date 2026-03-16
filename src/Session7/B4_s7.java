package Session7;
import java.util.*;

public class B4_s7 {
    static class Order {
        private String id;

        public Order(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    interface OrderRepository {
        void save(Order order);
        List<Order> findAll();
    }

    interface NotificationService {
        void send(String message, String recipient);
    }

    static class FileOrderRepository implements OrderRepository {

        private List<Order> orders = new ArrayList<>();

        @Override
        public void save(Order order) {
            orders.add(order);
            System.out.println("Lưu đơn hàng vào file: " + order.getId());
        }

        @Override
        public List<Order> findAll() {
            return orders;
        }
    }

    static class DatabaseOrderRepository implements OrderRepository {

        private List<Order> orders = new ArrayList<>();

        @Override
        public void save(Order order) {
            orders.add(order);
            System.out.println("Lưu đơn hàng vào database: " + order.getId());
        }

        @Override
        public List<Order> findAll() {
            return orders;
        }
    }

    static class EmailService implements NotificationService {

        @Override
        public void send(String message, String recipient) {
            System.out.println("Gửi email: " + message);
        }
    }

    static class SMSNotification implements NotificationService {

        @Override
        public void send(String message, String recipient) {
            System.out.println("Gửi SMS: " + message);
        }
    }

    static class OrderService {

        private OrderRepository orderRepository;
        private NotificationService notificationService;

        public OrderService(OrderRepository orderRepository,
                            NotificationService notificationService) {
            this.orderRepository = orderRepository;
            this.notificationService = notificationService;
        }

        public void createOrder(String id, String customer) {

            Order order = new Order(id);

            orderRepository.save(order);

            notificationService.send(
                    "Đơn hàng " + id + " đã được tạo",
                    customer
            );
        }
    }

    public static void main(String[] args) {

        System.out.println("Dùng FileOrderRepository và EmailService");

        OrderRepository repo1 = new FileOrderRepository();
        NotificationService notify1 = new EmailService();

        OrderService service1 = new OrderService(repo1, notify1);

        service1.createOrder("ORD001", "a@example.com");

        System.out.println();

        System.out.println("Đổi sang DatabaseOrderRepository và SMSNotification");

        OrderRepository repo2 = new DatabaseOrderRepository();
        NotificationService notify2 = new SMSNotification();

        OrderService service2 = new OrderService(repo2, notify2);

        service2.createOrder("ORD002", "0123456789");
    }
}
