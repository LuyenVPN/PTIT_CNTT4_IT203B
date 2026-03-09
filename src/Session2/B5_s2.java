package Session2;

interface UserActions {
    default void logActivity(String activity) {
        System.out.println("User action: " + activity);
    }
}

interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("Admin action: " + activity);
    }
}

class SuperAdmin implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {
        System.out.println("SuperAdmin activity: " + activity);

        // gọi method của từng interface nếu muốn
        UserActions.super.logActivity(activity);
        AdminActions.super.logActivity(activity);
    }
}

public class B5_s2 {
    public static void main(String[] args) {

        SuperAdmin superadmin = new SuperAdmin();

        superadmin.logActivity("Delete user");
    }
}
