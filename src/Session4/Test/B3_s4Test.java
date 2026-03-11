import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class B3_s4Test {
    B3_s4 listTest;
    @BeforeEach
    void setUp() {
        listTest = new B3_s4();
    }

    @Test
    void testValidEmail() {
        assertEquals("user@gmail.com", listTest.processEmail("user@gmail.com"));
    }

    @Test
    void testEmailMissingAt() {
        assertThrows(IllegalArgumentException.class, () -> listTest.processEmail("usergmail.com"));
    }

    @Test
    void testEmailMissingDomain() {
        assertThrows(IllegalArgumentException.class, () -> listTest.processEmail("user@"));
    }

    @Test
    void testEmailNormalization() {
        assertEquals("example@gmail.com", listTest.processEmail("Example@Gmail.com"));
    }
    @Test
    void testValidate() {
        assertEquals("user@gmail,com", listTest.processEmail("usergmailcom"));
    }
}