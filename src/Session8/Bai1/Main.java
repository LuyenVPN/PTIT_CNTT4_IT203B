package Session8.Bai1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Device> devices = new ArrayList<>();
        HardwareConnection connection = null;

        while (true) {
            System.out.println("\n1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Tắt thiết bị");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    connection = HardwareConnection.getInstance();
                    connection.connect();
                    break;

                case 2:
                    System.out.println("1. Đèn  2. Quạt  3. Điều hòa");
                    System.out.print("Chọn loại: ");
                    int type = sc.nextInt();
                    DeviceFactory factory = null;

                    if (type == 1) factory = new LightFactory();
                    else if (type == 2) factory = new FanFactory();
                    else if (type == 3) factory = new AirConditionerFactory();

                    if (factory != null) {
                        Device d = factory.createDevice();
                        devices.add(d);
                    }
                    break;

                case 3:
                    if (devices.isEmpty()) {
                        System.out.println("Chưa có thiết bị.");
                        break;
                    }
                    for (int i = 0; i < devices.size(); i++) {
                        System.out.println((i + 1) + ". Device " + (i + 1));
                    }
                    System.out.print("Chọn: ");
                    int onIndex = sc.nextInt();
                    devices.get(onIndex - 1).turnOn();
                    break;

                case 4:
                    if (devices.isEmpty()) {
                        System.out.println("Chưa có thiết bị.");
                        break;
                    }
                    for (int i = 0; i < devices.size(); i++) {
                        System.out.println((i + 1) + ". Device " + (i + 1));
                    }
                    System.out.print("Chọn: ");
                    int offIndex = sc.nextInt();
                    devices.get(offIndex - 1).turnOff();
                    break;

                case 5:
                    return;
            }
        }
    }
}
