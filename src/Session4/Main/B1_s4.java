public class B1_s4 {

    public static boolean isValidUsername(String username) {

        if (username == null) {
            return false;
        }

        int length = username.length();

        if (length < 6 || length > 20) {
            return false;
        }

        if (username.contains(" ")) {
            return false;
        }

        return true;
    }
}
