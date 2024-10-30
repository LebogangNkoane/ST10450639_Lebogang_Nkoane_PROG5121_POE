/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registerandlogin;

/**
 *
 * @author RC_Student_lab Lebogang Nkoane
 */
public class Login {
    // The Login class is responsible for handling user authentication by managing registration details and validating login attempts against stored credentials. 
    // It ensures that usernames and passwords adhere to specified formatting requirements, thus providing a secure way for users to access their tasks.

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    // This Registers the user by validating the format of the username and password, storing the details if they meet the required criteria.
    public String registerUser(String user, String pass, String fName, String lName) {
        if (!checkUserName(user)) {
            // Returns an error message if the username does not meet formatting rules, prompting the user to correct it.
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        if (!checkPasswordComplexity(pass)) {
            // Returns an error message if the password does not meet complexity requirements, encouraging the user to create a stronger password.
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }

        // This Stores registration details in instance variables for use in future login attempts.
        this.username = user;
        this.password = pass;
        this.firstName = fName;
        this.lastName = lName;

        // This Returns a success message indicating that the registration has been completed successfully.
        return "Username and password successfully captured. User registered!";
    }

    // This Validatess the login credentials by comparing the entered username and password against the stored values, returning true if they match.
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    // This Returns a message that greets the user upon successful login or indicates failure if the credentials are incorrect.
    public String returnLoginStatus(boolean loginSuccess) {
        return loginSuccess ? "Welcome " + firstName + " " + lastName + ", it is great to see you again." : "Username or password incorrect, please try again.";
    }

    // This Validates the formatting requirements of the username, ensuring it contains an underscore and is no longer than five characters.
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // This Validates the complexity of the password by checking for length, the presence of uppercase letters, numbers, and special characters to ensure security.
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapitalLetter = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialCharacter = password.matches(".*[!@#$%^&*()].*");
        return password.length() >= 8 && hasCapitalLetter && hasNumber && hasSpecialCharacter;
    }
}

