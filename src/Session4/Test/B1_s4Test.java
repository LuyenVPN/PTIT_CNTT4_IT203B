import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class B1_s4Test {

    @Test
    void TC01_validUsername() {
        // Arrange
        String username = "user123";
        // Act
        boolean result = B1_s4.isValidUsername(username);
        // Assert
        assertTrue(result);
    }

    @Test
    void TC02_usernameTooShort() {

        // Arrange
        String username = "abc";

        // Act
        boolean result = B1_s4.isValidUsername(username);

        // Assert
        assertFalse(result);
    }

    @Test
    void TC03_usernameContainsSpace() {

        // Arrange
        String username = "user name";

        // Act
        boolean result = B1_s4.isValidUsername(username);

        // Assert
        assertFalse(result);
    }
}