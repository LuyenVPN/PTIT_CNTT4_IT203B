package Session1;

public class B3_s1 {
    public static class User {
        private int age;

        public void setAge(int age) {
            if (age < 0) {
                throw new IllegalArgumentException("Tuổi không thể âm!");
            }
            this.age = age;
        }
    }

    public static void main(String[] args) {
        User user=new User();
        user.setAge(20);
        System.out.printf("Tuổi của người dùng: %d\n", user.age);
        user.setAge(-5);
    }
}
