/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registerandlogin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */

public class RegisterAndLoginTestTest {

    // Username is correctly formatted (contains an underscore and is <= 5 characters)
    @Test
    public void testCorrectUsernameFormat() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "Kyle", "Buden");
        assertTrue(user.checkUserName());
    }

    // Username is incorrectly formatted (does not contain an underscore or > 5 characters)
    @Test
    public void testIncorrectUsernameFormat() {
        Login user = new Login("kyle!!!!!!!", "Ch&&sec@ke99!", "Kyle", "Buden");
        assertFalse(user.checkUserName());
    }

    // Password meets complexity requirements
    @Test
    public void testPasswordMeetsComplexity() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "Kyle", "Buden");
        assertTrue(user.checkPasswordComplexity());
    }

    // Password does not meet complexity requirements
    @Test
    public void testPasswordFailsComplexity() {
        Login user = new Login("kyl_1", "password", "Kyle", "Buden");
        assertFalse(user.checkPasswordComplexity());
    }

    // Registration successful (username and password are both correct)
    @Test
    public void testSuccessfulRegistration() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "Kyle", "Buden");
        assertEquals("Username and password successfully captured. User registered!", user.registerUser());
    }

    // Registration failed due to incorrect username
    @Test
    public void testRegistrationFailUsername() {
        Login user = new Login("kyle!!!!!!!", "Ch&&sec@ke99!", "Kyle", "Buden");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", user.registerUser());
    }

    // Registration failed due to incorrect password
    @Test
    public void testRegistrationFailPassword() {
        Login user = new Login("kyl_1", "password", "Kyle", "Buden");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", user.registerUser());
    }

    // Login successful
    @Test
    public void testLoginSuccessful() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "Kyle", "Buden");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    // Login failed due to incorrect password
    @Test
    public void testLoginFailed() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "Kyle", "Buden");
        assertFalse(user.loginUser("kyl_1", "wrongpassword"));
    }

    // Login message after successful login
    @Test
    public void testReturnLoginStatusSuccess() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "Kyle", "Buden");
        boolean loginSuccess = user.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle Buden, it is great to see you again.", user.returnLoginStatus(loginSuccess));
    }

    // Login message after failed login
    @Test
    public void testReturnLoginStatusFailed() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "Kyle", "Buden");
        boolean loginSuccess = user.loginUser("kyl_1", "wrongpassword");
        assertEquals("Username or password incorrect, please try again.", user.returnLoginStatus(loginSuccess));
    }
}

