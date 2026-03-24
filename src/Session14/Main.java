package Session14;

import Session14.service.FlashSaleService;

public class Main {
    public static void main(String[] args) {

        FlashSaleService service = new FlashSaleService();

        for (int i = 0; i < 50; i++) {
            int userId = (i % 3) + 1;

            new Thread(() -> {
                service.placeOrder(userId, 1, 1);
            }).start();
        }
    }
}
