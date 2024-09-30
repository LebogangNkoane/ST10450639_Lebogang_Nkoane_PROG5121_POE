/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registerandlogin;
import java.util.*;
/**
 *
 * @author RC_Student_lab ST10450639_Lebogang_Nkoane
 */

public class RegisterAndLogin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // User registration input
        System.out.println("PLEASE REGISTER");
        
        System.out.println("Enter your first name: ");
        String firstName = sc.next();

        System.out.println("Enter your last name: ");
        String lastName = sc.next();
        System.out.println("Enter a username: ");
        String username = sc.next();

        System.out.println("Enter a password: ");
        String password = sc.next();

        // Creating a Login object
        Login user = new Login(username, password, firstName, lastName);

        // Registering the user
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        // If registration is successful, prompt user to login
        if (registrationMessage.equals("Username and password successfully captured. User registered!")) {
            
            System.out.println("PLEASE LOGIN");

            System.out.println("Enter your username: ");
            String enteredUsername = sc.next();

            System.out.println("Enter your password: ");
            String enteredPassword = sc.next();

            // Login the user
            boolean loginSuccess = user.loginUser(enteredUsername, enteredPassword);
            String loginMessage = user.returnLoginStatus(loginSuccess);
            System.out.println(loginMessage);
        }
        
        sc.close();
    }
}
