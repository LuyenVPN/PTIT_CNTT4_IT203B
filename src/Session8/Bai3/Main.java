package Session8.Bai3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        RemoteControl remote = new RemoteControl();

        while (true) {
            System.out.println("\n1. Gán nút");
            System.out.println("2. Nhấn nút");
            System.out.println("3. Undo");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Chọn nút: ");
                    int btn = sc.nextInt();

                    System.out.println("1.Light On  2.Light Off  3.Fan On  4.Fan Off  5.AC Set Temp");
                    int type = sc.nextInt();

                    if (type == 1) remote.setCommand(btn, new LightOnCommand(light));
                    else if (type == 2) remote.setCommand(btn, new LightOffCommand(light));
                    else if (type == 3) remote.setCommand(btn, new FanOnCommand(fan));
                    else if (type == 4) remote.setCommand(btn, new FanOffCommand(fan));
                    else if (type == 5) {
                        System.out.print("Nhập nhiệt độ: ");
                        int t = sc.nextInt();
                        remote.setCommand(btn, new ACSetTemperatureCommand(ac, t));
                    }
                    break;

                case 2:
                    System.out.print("Nhấn nút: ");
                    int press = sc.nextInt();
                    remote.pressButton(press);
                    break;

                case 3:
                    remote.undo();
                    break;

                case 4:
                    return;
            }
        }
    }
}
