package Session9.util;

public class Logger {
    public static synchronized void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}
