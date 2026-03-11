public class B5_s4 {

    public boolean canPerformAction(User user, Action action) {

        Role role = user.getRole();

        if (role == Role.ADMIN) return true;

        if (role == Role.MODERATOR) {
            return action == Action.LOCK_USER ||
                    action == Action.VIEW_PROFILE;
        }

        if (role == Role.USER) {
            return action == Action.VIEW_PROFILE;
        }

        return false;
    }

    public enum Role {
        ADMIN, MODERATOR, USER
    }

    public enum Action {
        DELETE_USER, LOCK_USER, VIEW_PROFILE
    }

    public class User {

        private Role role;

        public User(Role role) {
            this.role = role;
        }

        public Role getRole() {
            return role;
        }
    }
}