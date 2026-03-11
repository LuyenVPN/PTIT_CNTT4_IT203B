import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class B5_s4Test {

    B5_s4 ps;
    B5_s4.User admin;
    B5_s4.User mod;
    B5_s4.User user;

    @BeforeEach
    void setup() {
        ps = new B5_s4();

        admin = ps.new User(B5_s4.Role.ADMIN);
        mod = ps.new User(B5_s4.Role.MODERATOR);
        user = ps.new User(B5_s4.Role.USER);
    }

    @AfterEach
    void cleanup() {
        admin = null;
        mod = null;
        user = null;
    }

    @Test
    void adminPermissions() {
        assertTrue(ps.canPerformAction(admin, B5_s4.Action.DELETE_USER));
        assertTrue(ps.canPerformAction(admin, B5_s4.Action.LOCK_USER));
        assertTrue(ps.canPerformAction(admin, B5_s4.Action.VIEW_PROFILE));
    }

    @Test
    void moderatorPermissions() {
        assertFalse(ps.canPerformAction(mod, B5_s4.Action.DELETE_USER));
        assertTrue(ps.canPerformAction(mod, B5_s4.Action.LOCK_USER));
        assertTrue(ps.canPerformAction(mod, B5_s4.Action.VIEW_PROFILE));
    }

    @Test
    void userPermissions() {
        assertFalse(ps.canPerformAction(user, B5_s4.Action.DELETE_USER));
        assertFalse(ps.canPerformAction(user, B5_s4.Action.LOCK_USER));
        assertTrue(ps.canPerformAction(user, B5_s4.Action.VIEW_PROFILE));
    }
}