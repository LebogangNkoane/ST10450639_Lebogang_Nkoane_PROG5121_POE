/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registerandlogin;

/**
 *
 * @author RC_Student_lab ST10450639_LeboganG_Nkoane
 */
public class Login {
    
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    
    // Constructor
    public Login(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    // Method to check if the username contains an underscore and is no more than 5 characters long
    public boolean checkUserName() {
        if (username.contains("_") && username.length() <= 5) {
            return true; // Username is correct
        } else {
            return false; // Username is incorrect
        }
    }
    
    // Method to check if the password meets the complexity requirements
    public boolean checkPasswordComplexity() {
        // Password has to at least have 8 characters, a capital letter, number, and special character
        boolean hasCapitalLetter = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialCharacter = password.matches(".*[!@#$%^&*()].*");
        
        if (password.length() >= 8 && hasCapitalLetter && hasNumber && hasSpecialCharacter) {
            return true; // Password is correct
        } else {
            return false; // Password is incorrect
        }
    }
    
    // Method to register the user and return the right messages
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        
        return "Username and password successfully captured. User registered!";
    }
    
    // Method to login the user by verifying username and password
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
            return true; // Login successful
        } else {
            return false; // Login failed
        }
    }
    
    // Method to return the login status message
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
