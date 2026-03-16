package Session7;

public class B3_s7 {
    interface PaymentMethod {
        void pay(double amount);
    }

    interface CODPayable extends PaymentMethod {
    }

    interface CardPayable extends PaymentMethod {
    }

    interface EWalletPayable extends PaymentMethod {
    }

    static class CODPayment implements CODPayable {

        @Override
        public void pay(double amount) {
            System.out.println("Xử lý thanh toán COD: " + amount + " - Thành công");
        }
    }

    static class CreditCardPayment implements CardPayable {

        @Override
        public void pay(double amount) {
            System.out.println("Xử lý thanh toán thẻ tín dụng: " + amount + " - Thành công");
        }
    }

    static class MomoPayment implements EWalletPayable {

        @Override
        public void pay(double amount) {
            System.out.println("Xử lý thanh toán MoMo: " + amount + " - Thành công");
        }
    }

    static class PaymentProcessor {

        private PaymentMethod paymentMethod;

        public PaymentProcessor(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public void process(double amount) {
            paymentMethod.pay(amount);
        }
    }


    public static void main(String[] args) {

        System.out.println("Thanh toán COD");
        PaymentProcessor cod = new PaymentProcessor(new CODPayment());
        cod.process(500000);

        System.out.println();

        System.out.println("Thanh toán thẻ tín dụng");
        PaymentProcessor card = new PaymentProcessor(new CreditCardPayment());
        card.process(1000000);

        System.out.println();

        System.out.println("Thanh toán MoMo");
        PaymentProcessor momo = new PaymentProcessor(new MomoPayment());
        momo.process(750000);

        System.out.println();

        System.out.println("Kiểm tra LSP - thay CreditCard bằng Momo");
        PaymentProcessor test = new PaymentProcessor(new MomoPayment());
        test.process(1000000);
    }
}
