package Session8.Bai1;

public class HardwareConnection {
    private static HardwareConnection instance;
    private boolean connected = false;

    private HardwareConnection() {
    }

    public static HardwareConnection getInstance() {
        if (instance == null) {
            instance = new HardwareConnection();
        }
        return instance;
    }

    public void connect() {
        if (!connected) {
            connected = true;
            System.out.println("HardwareConnection: Đã kết nối phần cứng.");
        }
    }

    public void disconnect() {
        if (connected) {
            connected = false;
            System.out.println("HardwareConnection: Đã ngắt kết nối.");
        }
    }
}
