/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registerandlogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class RegisterAndLoginTestTest {
    
    public RegisterAndLoginTestTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // Test Case 1: Valid Username Format
   // This test checks if a username that meets the formatting criteria (contains an underscore and is <= 5 characters) is recognized as valid.
    @Test
    public void testCorrectUsernameFormat() {
        Login user = new Login();
        assertTrue(user.checkUserName("kyl_1"), "The username should be correctly formatted with an underscore and 5 characters or less.");
    }

    // Test Case 2: Invalid Username Format
    // This test verifies that a username which does not contain an underscore or exceeds the length of 5 characters is rejected as invalid.
    @Test
    public void testIncorrectUsernameFormat() {
        Login user = new Login();
        assertFalse(user.checkUserName("kyle!!!!!!!"), "The username should not be valid as it exceeds the length and contains invalid characters.");
    }

    // Test Case 3: Password Complexity Validation
    // This test checks whether a password that satisfies all complexity requirements (length, uppercase letter, number, special character) is accepted as valid.
    @Test
    public void testPasswordMeetsComplexity() {
        Login user = new Login();
        assertTrue(user.checkPasswordComplexity("Ch&&sec@ke99!"), "The password should meet all complexity requirements.");
    }

    // Test Case 4: Password Complexity Failure
    // This test ensures that a password lacking complexity (e.g., no uppercase letter, number, or special character) is identified as invalid.
    @Test
    public void testPasswordFailsComplexity() {
        Login user = new Login();
        assertFalse(user.checkPasswordComplexity("password"), "The password should be considered invalid due to lack of complexity.");
    }

    // Test Case 5: Successful User Registration
    // This test verifies that valid username and password inputs lead to a successful registration message.
    @Test
    public void testSuccessfulRegistration() {
        Login user = new Login();
        assertEquals("Username and password successfully captured. User registered!", 
                     user.registerUser("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith"),
                     "The registration should succeed with valid username and password.");
    }

    // Test Case 6: Registration Failure due to Invalid Username
    // This test checks that a registration attempt fails when the username is incorrectly formatted.
    @Test
    public void testRegistrationFailUsername() {
        Login user = new Login();
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", 
                     user.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "Kyle", "Smith"),
                     "The registration should fail due to incorrectly formatted username.");
    }

    // Test Case 7: Registration Failure due to Invalid Password
    // This test ensures that registration fails when the provided password does not meet the complexity requirements.
    @Test
    public void testRegistrationFailPassword() {
        Login user = new Login();
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.", 
                     user.registerUser("kyl_1", "password", "Kyle", "Smith"),
                     "The registration should fail due to invalid password format.");
    }

    // Test Case 8: Successful Login
    // This test checks if the login process succeeds with the correct username and password, ensuring proper authentication.
    @Test
    public void testLoginSuccessful() {
        Login user = new Login();
        user.registerUser("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith"); // Register the user first
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"), "Login should succeed with correct credentials.");
    }

    // Test Case 9: Failed Login due to Incorrect Password
    // This test verifies that an attempt to login with an incorrect password results in a failed authentication.
    @Test
    public void testLoginFailed() {
        Login user = new Login();
        user.registerUser("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith"); // Register the user first
        assertFalse(user.loginUser("kyl_1", "wrongpassword"), "Login should fail due to incorrect password.");
    }

    // Test Case 10: Login Success Message
    // This test confirms that the correct success message is returned upon a successful login attempt.
    @Test
    public void testReturnLoginStatusSuccess() {
        Login user = new Login();
        user.registerUser("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith"); // Register the user first
        boolean loginSuccess = user.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle Buden, it is great to see you again.", user.returnLoginStatus(loginSuccess), "The success message should reflect the user's name.");
    }

    // Test Case 11: Login Failure Message
    // This test checks that the appropriate failure message is returned when a login attempt fails.
    @Test
    public void testReturnLoginStatusFailed() {
        Login user = new Login();
        user.registerUser("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith"); // Register the user first
        boolean loginSuccess = user.loginUser("kyl_1", "wrongpassword");
        assertEquals("Username or password incorrect, please try again.", user.returnLoginStatus(loginSuccess), "The failure message should indicate incorrect credentials.");
    }
}
