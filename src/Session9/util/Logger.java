package Session9.util;

public class Logger {
    private static final long startTime = System.currentTimeMillis();

    public static synchronized void log(String msg) {

        long currentTime = (System.currentTimeMillis() - startTime) / 1000;

        System.out.println("[Time: " + String.format("%02d", currentTime) + "s] " + msg);    }
}
