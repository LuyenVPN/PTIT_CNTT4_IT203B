public class B4_s4 {
    public String evaluatePasswordStrength(String password) {

        if (password.length() < 8) return "Yếu";

        boolean upper = password.matches(".*[A-Z].*");
        boolean lower = password.matches(".*[a-z].*");
        boolean number = password.matches(".*[0-9].*");
        boolean special = password.matches(".*[^a-zA-Z0-9].*");

        if (upper && lower && number && special) return "Mạnh";

        if (upper || lower || number || special) return "Trung bình";

        return "Yếu";
    }
}
