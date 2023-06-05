package org.example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;
    private User user;

    @BeforeEach
    public void setup() {
        userService = new UserService();
        user = new User("Alice", "password123", "alice@example.com");
        userService.registerUser(user);
    }

    @AfterEach
    public void teardown() {
        userService = null;
        user = null;
    }

    @Test
    public void testRegisterNewUser() {
        User newUser = new User("Joe", "newpassword", "joe@example.com");
        assertTrue(userService.registerUser(newUser));
    }

    @Test
    public void testRegisterExistingUser() {
        assertFalse(userService.registerUser(user));
    }

    @Test
    public void testLoginUser() {
        User loggedInUser = userService.loginUser("Alice", "password123");
        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
    }

    @Test
    public void testLoginUserWrongUsername() {
        assertNull(userService.loginUser("NoUser", "password123"));
    }

    @Test
    public void testLoginUserWrongPassword() {
        assertNull(userService.loginUser("Alice", "wrongpassword"));
    }
}
