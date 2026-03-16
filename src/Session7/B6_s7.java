package Session7;

import java.text.NumberFormat;
import java.util.Scanner;

public class B6_s7 {
    interface DiscountStrategy {
        double apply(double amount);
    }

    interface PaymentMethod {
        void pay(double amount);
    }

    interface NotificationService {
        void send(String message);
    }

    static class WebsiteDiscount implements DiscountStrategy {
        public double apply(double amount) {
            System.out.println("Áp dụng giảm giá 10% cho đơn hàng website");
            return amount * 0.9;
        }
    }

    static class WebsitePayment implements PaymentMethod {
        public void pay(double amount) {
            System.out.println("Xử lý thanh toán thẻ tín dụng qua cổng thanh toán online");
        }
    }

    static class EmailNotification implements NotificationService {
        public void send(String message) {
            System.out.println("Gửi email xác nhận");
        }
    }

    static class MobileFirstOrderDiscount implements DiscountStrategy {
        public double apply(double amount) {
            System.out.println("Áp dụng giảm giá 15% cho lần đầu");
            return amount * 0.85;
        }
    }

    static class MomoPayment implements PaymentMethod {
        public void pay(double amount) {
            System.out.println("Xử lý thanh toán MoMo tích hợp");
        }
    }

    static class PushNotification implements NotificationService {
        public void send(String message) {
            System.out.println("Gửi push notification: " + message);
        }
    }

    static class MemberDiscount implements DiscountStrategy {
        public double apply(double amount) {
            System.out.println("Áp dụng giảm giá thành viên 5%");
            return amount * 0.95;
        }
    }

    static class CashPayment implements PaymentMethod {
        public void pay(double amount) {
            System.out.println("Thanh toán tiền mặt tại quầy");
        }
    }

    static class PrintReceipt implements NotificationService {
        public void send(String message) {
            System.out.println("In hóa đơn giấy");
        }
    }

    interface SalesChannelFactory {

        DiscountStrategy createDiscount();

        PaymentMethod createPayment();

        NotificationService createNotification();
    }

    static class WebsiteFactory implements SalesChannelFactory {

        public DiscountStrategy createDiscount() {
            return new WebsiteDiscount();
        }

        public PaymentMethod createPayment() {
            return new WebsitePayment();
        }

        public NotificationService createNotification() {
            return new EmailNotification();
        }
    }

    static class MobileAppFactory implements SalesChannelFactory {

        public DiscountStrategy createDiscount() {
            return new MobileFirstOrderDiscount();
        }

        public PaymentMethod createPayment() {
            return new MomoPayment();
        }

        public NotificationService createNotification() {
            return new PushNotification();
        }
    }

    static class StorePOSFactory implements SalesChannelFactory {

        public DiscountStrategy createDiscount() {
            return new MemberDiscount();
        }

        public PaymentMethod createPayment() {
            return new CashPayment();
        }

        public NotificationService createNotification() {
            return new PrintReceipt();
        }
    }

    static class OrderService {

        private DiscountStrategy discount;
        private PaymentMethod payment;
        private NotificationService notification;

        public OrderService(SalesChannelFactory factory) {

            discount = factory.createDiscount();
            payment = factory.createPayment();
            notification = factory.createNotification();
        }

        public void processOrder(String product, double price) {

            System.out.println("Sản phẩm: " + product);
            NumberFormat nf  = NumberFormat.getCurrencyInstance();
            double finalAmount = discount.apply(price);

            payment.pay(finalAmount);

            notification.send("Đơn hàng thành công");

            System.out.println("Số tiền cần thanh toán: " + nf.format(finalAmount));
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Chọn kênh bán:");
        System.out.println("1. Website");
        System.out.println("2. Mobile App");
        System.out.println("3. Store POS");
        System.out.println("Nhập lựa chọn của bạn: ");
        int choice = sc.nextInt();

        SalesChannelFactory factory = null;

        switch (choice) {

            case 1:
                factory = new WebsiteFactory();
                System.out.println("Bạn đã chọn kênh Website");
                break;

            case 2:
                factory = new MobileAppFactory();
                System.out.println("Bạn đã chọn kênh Mobile App");
                break;

            case 3:
                factory = new StorePOSFactory();
                System.out.println("Bạn đã chọn kênh POS");
                break;
        }

        OrderService service = new OrderService(factory);

        service.processOrder("Laptop", 15000000);
    }
}
