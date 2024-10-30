/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.registerandlogin;
import javax.swing.*; 
import java.util.ArrayList; 
import java.util.Scanner;
/**
 *
 * @author RC_Student_lab Lebogang Nkoane
 */
public class RegisterAndLogin {
    // The RegisterAndLogin class serves as the primary entry point for the application, orchestrating the user registration and login processes while managing task-related functionalities. 
    // It interacts with users by prompting them for their registration details, facilitating login attempts, and displaying a menu that allows users to manage their tasks effectively.

    private static ArrayList<Task> tasks = new ArrayList<>(); // This ArrayList stores all the tasks that users create within the application, allowing for easy management and retrieval.
    private static int totalHours = 0; // This variable keeps track of the total hours across all tasks, providing a cumulative view of the time spent on various assignments.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // This Prompts user registration by collecting essential information, including first name, last name, username, and password.
        System.out.print("PLEASE REGISTER\nEnter your first name: ");
        String firstName = sc.next();

        System.out.print("Enter your last name: ");
        String lastName = sc.next();

        System.out.print("Enter a username (Should contain an underscore and must be no more than 5 characters): ");
        String username = sc.next();

        System.out.print("Enter a password (Should contain at least 8 characters, a capital letter, a number, and a special character): ");
        String password = sc.next();

        // This Creates a Login object and register the user by calling the registerUser method with the provided details, capturing the registration status message.
        Login user = new Login();
        String registrationMessage = user.registerUser(username, password, firstName, lastName);
        System.out.println(registrationMessage);

        // If registration is successful, it prompts the user for login credentials to authenticate their access to the application.
        if (registrationMessage.equals("Username and password successfully captured. User registered!")) {
            System.out.print("\nPLEASE LOGIN\nEnter your username: ");
            String enteredUsername = sc.next();

            System.out.print("Enter your password: ");
            String enteredPassword = sc.next();

            // This Attempts to log in the user and provide feedback on the success or failure of the login attempt.
            boolean loginSuccess = user.loginUser(enteredUsername, enteredPassword);
            String loginMessage = user.returnLoginStatus(loginSuccess);
            System.out.println(loginMessage);
            
            sc.close();

            // If the login is successful, it proceeds to display the task management menu for further interactions.
            if (loginSuccess) {
                displayMenu(); // Shows the task menu
            }
            
        }
    }

    // This Displays the main menu using JOptionPane, providing users with options to manage tasks or exit the application.
    private static void displayMenu() {
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        // This Shows a welcome message to the user upon accessing the main menu.
        JOptionPane.showMessageDialog(dialog, "Welcome to EasyKanban");

        int option;
        do {
            // This Constructs the menu prompt for user selection, offering choices for adding tasks, generating reports, or quitting the application.
            String menu = "Select an option:\n1) Add Tasks\n2) Show Report\n3) Quit";
            String response = JOptionPane.showInputDialog(dialog, menu);
            option = Integer.parseInt(response);

            // This Processes the user's selection by executing the corresponding functionality based on the chosen option.
            switch (option) {
                case 1:
                    addTasks(dialog); // Invokes the method to add tasks
                    break;
                case 2:
                    // This is Currently a placeholder informing the user of its upcoming availability.
                    JOptionPane.showMessageDialog(dialog, "Coming Soon"); 
                    break;
                case 3:
                    // This Provides a goodbye message and terminate the application gracefully when the user opts to quit.
                    JOptionPane.showMessageDialog(dialog, "Goodbye!");
                    System.exit(0);
                    break;
                default:
                    // This Alerts the user if they enter an invalid option, prompting them to make a valid selection.
                    JOptionPane.showMessageDialog(dialog, "Invalid option. Please try again.");
            }
        } while (option != 3); // Continues displaying the menu until the user chooses to quit.

        dialog.dispose(); // Disposes of the dialog when done to free resources.
    }

    // This Collects user input to add tasks using JOptionPane, ensuring that each task meets specified requirements and is properly recorded.
    private static void addTasks(JDialog dialog) {
        String response = JOptionPane.showInputDialog(dialog, "How many tasks would you like to enter?");
        int numTasks = Integer.parseInt(response);

        // It Iterates through the number of tasks the user wants to enter, gathering the necessary information for each task.
        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog(dialog, "Enter Task Name:");
            String description;

            // This Validates the task description input to ensure it meets the maximum character limit before proceeding.
            do {
                description = JOptionPane.showInputDialog(dialog, "Enter Task Description (max 50 characters):");
                if (!Task.checkTaskDescription(description)) {
                    // This Notifies the user if the description exceeds the character limit and prompt for re-entry.
                    JOptionPane.showMessageDialog(dialog, "Please enter a task description of less than 50 characters.");
                } else {
                    // This Confirms successful entry of the task description to the user.
                    JOptionPane.showMessageDialog(dialog, "Task successfully captured.");
                }
            } while (!Task.checkTaskDescription(description));

            // This Collects developer information and the estimated duration of the task from the user.
            String developerName = JOptionPane.showInputDialog(dialog, "Enter Developer's First and Last Name:");
            String durationResponse = JOptionPane.showInputDialog(dialog, "Enter Task Duration (in hours):");
            int duration = Integer.parseInt(durationResponse);

            // This Presents the user with options for task status and retrieves their selection.
            String[] statusOptions = {"To Do", "Done", "Doing"};
            String status = (String) JOptionPane.showInputDialog(dialog, "Select Task Status:", "Task Status", JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            // This Creates a new Task object with the provided information and adds it to the tasks list while updating the total hours tracked.
            Task task = new Task(taskName, i, description, developerName, duration, status);
            tasks.add(task);
            totalHours += duration;

            // This Displays the details of the newly created task to the user in a formatted manner.
            JOptionPane.showMessageDialog(dialog, task.printTaskDetails());
        }

        // At the end of the task addition process, It informs the user of the total hours accumulated across all tasks.
        JOptionPane.showMessageDialog(dialog, "Total Hours across all tasks: " + totalHours);
    }
}


