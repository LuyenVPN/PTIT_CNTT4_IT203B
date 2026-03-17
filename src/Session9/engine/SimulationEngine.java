package Session9.engine;

import Session9.entity.Vehicle;
import Session9.pattern.factory.VehicleFactory;
import Session9.util.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimulationEngine là lớp điều phối chính của toàn bộ hệ thống
 * - Tạo phương tiện
 * - Chạy đèn giao thông
 * - Quản lý đa luồng
 */
public class SimulationEngine {

    // Giao lộ (tài nguyên dùng chung)
    private Intersection intersection = new Intersection();

    // Đèn giao thông
    private TrafficLight trafficLight = new TrafficLight();

    // Thread pool để quản lý nhiều xe cùng lúc
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * Hàm bắt đầu mô phỏng
     */
    public void start() {

        Logger.log("Bắt đầu mô phỏng giao thông...");

        // 1. Khởi động đèn giao thông (chạy nền - daemon thread)
        Thread lightThread = new Thread(trafficLight);
        lightThread.setDaemon(true); // khi main kết thúc thì thread này cũng dừng
        lightThread.start();

        // 2. Sinh phương tiện liên tục
        for (int i = 0; i < 20; i++) {

            // Tạo xe ngẫu nhiên bằng Factory Pattern
            Vehicle v = VehicleFactory.create(intersection);

            // Đăng ký xe với đèn giao thông (Observer Pattern)
            trafficLight.register(v);

            Logger.log("Sinh phương tiện: " + v.getId());

            // Đưa xe vào thread pool để chạy song song
            executor.submit(v);

            try {
                // Delay giữa các xe (tránh spawn quá nhanh)
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.log("Lỗi khi tạo xe: " + e.getMessage());
            }
        }

        // 3. Ngừng nhận thêm task mới
        executor.shutdown();

        Logger.log("Kết thúc mô phỏng");
    }
}
