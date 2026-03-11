import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class B2_s4Test {
    B2_s4 listTest = new B2_s4();

    @Test
    void testAge18() {
        assertEquals(true, listTest.checkRegistrationAge(18));
    }

    @Test
    void testAge17() {
        assertEquals(false, listTest.checkRegistrationAge(17));
    }

    @Test
    void testNegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            listTest.checkRegistrationAge(-1);
        });
    }
    @Test
    void testAge(){
        assertEquals(false, listTest.checkRegistrationAge(-1));
    }
}