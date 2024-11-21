/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.registerandlogin;
import javax.swing.*; 
import java.util.ArrayList; 

/**
 *
 * @author RC_Student_lab Lebogang Nkoane
 */
public class RegisterAndLogin {
    // Arrays (ArrayLists) to store task data such as developer names, task details, durations, and statuses.
    private static ArrayList<String> developers = new ArrayList<>();
    private static ArrayList<String> taskNames = new ArrayList<>();
    private static ArrayList<String> taskIDs = new ArrayList<>();
    private static ArrayList<Integer> taskDurations = new ArrayList<>();
    private static ArrayList<String> taskStatuses = new ArrayList<>();

    public static void main(String[] args) {
        // User registration: capture first name, last name, username, and password.
        String firstName = JOptionPane.showInputDialog("PLEASE REGISTER\nEnter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String username = JOptionPane.showInputDialog("Enter a username (max 5 characters with underscore):");
        String password = JOptionPane.showInputDialog("Enter a password (min 8 characters, 1 uppercase, 1 number, 1 special char):");

        // Create a new Login object to handle user registration.
        Login user = new Login();
        String registrationMessage = user.registerUser(username, password, firstName, lastName);
        JOptionPane.showMessageDialog(null, registrationMessage); // Display registration feedback.

        // Proceed to login if registration is successful.
        if (registrationMessage.contains("successfully captured")) {
            String enteredUsername = JOptionPane.showInputDialog("PLEASE LOGIN\nEnter your username:");
            String enteredPassword = JOptionPane.showInputDialog("Enter your password:");

            boolean loginSuccess = user.loginUser(enteredUsername, enteredPassword);
            String loginMessage = user.returnLoginStatus(loginSuccess);
            JOptionPane.showMessageDialog(null, loginMessage); // Display login status.

            // If login is successful, display the main task management menu.
            if (loginSuccess) {
                displayMenu(); // Method to show task management options.
            }
        }
    }

    // Displays the main menu with options for task management, including adding and searching tasks.
    private static void displayMenu() {
        String[] options = {"Add Tasks", "Show Report", "Display 'Done' Tasks", "Longest Task", "Search Task by Name", "Search Tasks by Developer", "Delete Task", "Quit"};
        while (true) {
            // User selects an option from the main menu.
            String selectedOption = (String) JOptionPane.showInputDialog(null, "Select an option:", "Task Management Menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            // Exit the application if the user selects "Quit" or cancels.
            if (selectedOption == null || selectedOption.equals("Quit")) {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                System.exit(0);
            }

            // Execute the appropriate method based on the user's selection.
            switch (selectedOption) {
                case "Add Tasks":
                    addTasks(); // Method to add new tasks.
                    break;
                case "Show Report":
                    showFullReport(); // Display a detailed report of all tasks.
                    break;
                case "Display 'Done' Tasks":
                    showDoneTasks(); // Show tasks with status 'Done'.
                    break;
                case "Longest Task":
                    displayLongestTask(); // Identify and display the longest task.
                    break;
                case "Search Task by Name":
                    searchTaskByName(); // Find and display task details by task name.
                    break;
                case "Search Tasks by Developer":
                    searchTasksByDeveloper(); // Display all tasks assigned to a specific developer.
                    break;
                case "Delete Task":
                    deleteTaskByName(); // Delete a task by its name.
                    break;
            }
        }
    }

    // Adds one or more tasks, prompting the user for task details.
    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks would you like to enter?"));
        for (int i = 0; i < numTasks; i++) {
            // Gather task details: task name, description, developer, duration, and status.
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            String description;
            do {
                description = JOptionPane.showInputDialog("Enter Task Description (max 50 characters):");
                // Validate that the description is within 50 characters.
                if (description.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Description must be 50 characters or less.");
                }
            } while (description.length() > 50);

            String developerName = JOptionPane.showInputDialog("Enter Developer's First and Last Name:");
            int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (in hours):"));

            // Allow the user to select a task status from predefined options.
            String[] statusOptions = {"To Do", "Done", "Doing"};
            String status = (String) JOptionPane.showInputDialog(null, "Select Task Status:", "Task Status", JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            // Generate a task ID based on the task name, task number, and developer's name.
            String taskID = createTaskID(taskName, i, developerName);

            // Store task details in respective arrays.
            developers.add(developerName);
            taskNames.add(taskName);
            taskIDs.add(taskID);
            taskDurations.add(duration);
            taskStatuses.add(status);

            // Display confirmation message with task details.
            JOptionPane.showMessageDialog(null, String.format("Task added:\nTask ID: %s\nTask: %s\nDeveloper: %s\nDuration: %d hours\nStatus: %s", taskID, taskName, developerName, duration, status));
        }
    }

    // Displays a full report of all tasks with their details.
    private static void showFullReport() {
        StringBuilder report = new StringBuilder("Full Task Report:\n");
        for (int i = 0; i < taskNames.size(); i++) {
            report.append(String.format("Task ID: %s\nDeveloper: %s\nTask: %s\nDuration: %d hours\nStatus: %s\n\n",
                    taskIDs.get(i), developers.get(i), taskNames.get(i), taskDurations.get(i), taskStatuses.get(i)));
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    // Displays tasks that have been marked as 'Done'.
    private static void showDoneTasks() {
        StringBuilder report = new StringBuilder("Tasks with 'Done' Status:\n");
        for (int i = 0; i < taskStatuses.size(); i++) {
            if ("Done".equalsIgnoreCase(taskStatuses.get(i))) {
                report.append(String.format("Developer: %s\nTask: %s\nDuration: %d hours\n\n",
                        developers.get(i), taskNames.get(i), taskDurations.get(i)));
            }
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    // Identifies and displays the task with the longest duration.
    private static void displayLongestTask() {
        int maxDurationIndex = 0;
        for (int i = 1; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(maxDurationIndex)) {
                maxDurationIndex = i;
            }
        }
        JOptionPane.showMessageDialog(null, String.format("Longest Task:\nDeveloper: %s\nTask: %s\nDuration: %d hours",
                developers.get(maxDurationIndex), taskNames.get(maxDurationIndex), taskDurations.get(maxDurationIndex)));
    }

    // Searches for a task by its name and displays its details if found.
    private static void searchTaskByName() {
        String searchName = JOptionPane.showInputDialog("Enter the Task Name to search:");
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(searchName)) {
                JOptionPane.showMessageDialog(null, String.format("Task Found:\nDeveloper: %s\nTask: %s\nStatus: %s",
                        developers.get(i), taskNames.get(i), taskStatuses.get(i)));
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    // Searches for all tasks assigned to a specific developer.
    private static void searchTasksByDeveloper() {
        String searchDeveloper = JOptionPane.showInputDialog("Enter the Developer's Name to search:");
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(searchDeveloper)) {
                report.append(String.format("Task: %s\nStatus: %s\n\n", taskNames.get(i), taskStatuses.get(i)));
            }
        }
        JOptionPane.showMessageDialog(null, report.length() > 0 ? report.toString() : "No tasks found for this developer.");
    }

    // Deletes a task based on its name.
    private static void deleteTaskByName() {
        String taskToDelete = JOptionPane.showInputDialog("Enter the Task Name to delete:");
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskToDelete)) {
                // Remove task details from all arrays.
                developers.remove(i);
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                JOptionPane.showMessageDialog(null, "Task deleted successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    // Generates a unique task ID using the first two characters of the task name, task number, and last three characters of the developer's name.
    private static String createTaskID(String taskName, int taskNumber, String developerName) {
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + developerName.substring(developerName.length() - 3).toUpperCase();
    }
}


