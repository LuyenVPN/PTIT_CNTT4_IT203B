import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class B4_s4Test {

    B4_s4 ps;

    @BeforeEach
    void setup() {
        ps = new B4_s4();
    }

    @Test
    void testPasswordStrengthLevels() {
        assertAll(
                () -> assertEquals("Mạnh",
                        ps.evaluatePasswordStrength("Abc123!@")),

                () -> assertEquals("Trung bình",
                        ps.evaluatePasswordStrength("abc123!@")),

                () -> assertEquals("Trung bình",
                        ps.evaluatePasswordStrength("ABC123!@")),

                () -> assertEquals("Trung bình",
                        ps.evaluatePasswordStrength("Abcdef!@")),

                () -> assertEquals("Trung bình",
                        ps.evaluatePasswordStrength("Abc12345")),

                () -> assertEquals("Yếu",
                        ps.evaluatePasswordStrength("Ab1!")),

                () -> assertEquals("Yếu",
                        ps.evaluatePasswordStrength("password")),

                () -> assertEquals("Yếu",
                        ps.evaluatePasswordStrength("ABC12345"))
        );
    }
    @Test
    void TestPass(){
        assertEquals("Mạnh",ps.evaluatePasswordStrength("A"));
    }
}