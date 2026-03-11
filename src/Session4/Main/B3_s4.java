public class B3_s4 {
    public String processEmail(String email) {
        if (!email.contains("@") || email.endsWith("@")) {
            throw new IllegalArgumentException("Email không hợp lệ!");
        }
        return email.toLowerCase();
    }
}
