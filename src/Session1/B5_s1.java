package Session1;

public class B5_s1 {
    static class User {

        private int age;

        public void setAge(int age) throws InvalidAgeException {

            if (age < 0) {
                throw new InvalidAgeException("Tuổi không thể âm!");
            }

            this.age = age;
        }

    }
    static class InvalidAgeException extends Exception {

        public InvalidAgeException(String msg) {
            super(msg);
        }

    }

    public static void main(String[] args) {

        User user = new User();

        try {
            user.setAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
        }

        System.out.println("Chương trình vẫn tiếp tục chạy.");
    }

}
