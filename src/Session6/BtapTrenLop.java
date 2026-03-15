package Session6;

import java.util.List;
import java.util.Random;

public class BtapTrenLop {
    public static class RandomStudent extends Thread {

        List<String> students = List.of(
                "An", "Bình", "Chi", "Dũng", "Hà",
                "Hùng", "Lan", "Minh", "Nam", "Phương"
        );

        public void run() {
            Random random = new Random();

            while (true) {
                int index = random.nextInt(students.size());
                System.out.println("Random: " + students.get(index));

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        RandomStudent t = new RandomStudent();
        t.start();
    }
}