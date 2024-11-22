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
    private static ArrayList<Task> tasks = new ArrayList<>(); // List to store tasks dynamically.
    private static ArrayList<String> developers = new ArrayList<>();
    private static ArrayList<String> taskNames = new ArrayList<>();
    private static ArrayList<String> taskIDs = new ArrayList<>();
    private static ArrayList<Integer> taskDurations = new ArrayList<>();
    private static ArrayList<String> taskStatuses = new ArrayList<>();
    private static int totalHours = 0; // Tracks the total hours spent on all tasks.

    public static void main(String[] args) {
        // Collect registration and login information using JDialog for input and messages
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true); // Ensure the dialog always appears on top   
        
        JOptionPane.showMessageDialog(dialog, "PLEASE REGISTER");
        
        // Collecting user input for registration
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String username = JOptionPane.showInputDialog("Enter a username (Should contain an underscore and be no more than 5 characters):");
        String password = JOptionPane.showInputDialog("Enter a password (At least 8 characters, with a capital letter, number, and special character):");

        // Register the user
        Login user = new Login();
        String registrationMessage = user.registerUser(username, password, firstName, lastName);
        
        // Show registration result message in JDialog
        JOptionPane.showMessageDialog(dialog, registrationMessage);

        // If registration is successful, proceed with login
        if (registrationMessage.contains("Username and password successfully captured. User registered")) {
            JOptionPane.showMessageDialog(dialog, "PLEASE LOGIN");
            // Collect login information
            String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
            String enteredPassword = JOptionPane.showInputDialog("Enter your password:");

            boolean loginSuccess = user.loginUser(enteredUsername, enteredPassword);
            String loginMessage = user.returnLoginStatus(loginSuccess);
            
            // Display login status in JDialog
            JOptionPane.showMessageDialog(dialog, loginMessage);

            // If login is successful, show the task management menu
            if (loginSuccess) {
                displayMenu();
            }
            dialog.dispose(); // Disposes the dialog when done to free resources.
        }
    }

    /**
     * Displays the main menu to the user for selecting different task management options.
     */
    private static void displayMenu() {
        final JDialog menuDialog = new JDialog(); 
        menuDialog.setAlwaysOnTop(true); // Set the dialog to always be on top
        menuDialog.setModal(true);
        JOptionPane.showMessageDialog(menuDialog, "Welcome to EasyKanban");
        
        int option;
        do {
            // Menu options displayed to the user
            String[] menuOptions = {"Add Tasks", "Show Report", "Display Tasks with 'Done' Status", "Display Tasks With Longest Duration", 
                                     "Search Task by Name", "Search Task by Developer", "Delete Task by Name", "Exit"};
            String response = (String) JOptionPane.showInputDialog(menuDialog, "Select an option:", "Main Menu", JOptionPane.QUESTION_MESSAGE, null, menuOptions, menuOptions[0]);

            // Handle menu option actions
            switch (response) {
                case "Add Tasks":
                    addTasks();
                    break;
                case "Show Report":
                    showReport();
                    break;
                case "Display Tasks with 'Done' Status":
                    displayTaskWithDoneStatus();
                    break;
                case "Display Tasks With Longest Duration":
                    displayTaskWithLongestDuration();
                    break;
                case "Search Task by Name":
                    searchTaskByName();
                    break;
                case "Search Task by Developer":
                    searchTaskByDeveloper();
                    break;
                case "Delete Task by Name":
                    deleteTaskByName();
                    break;
                case "Exit":
                    JOptionPane.showMessageDialog(menuDialog, "Exiting!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(menuDialog, "Invalid option. Please try again.");
            }
        } while (true);
    }

    /**
     * Handles adding new tasks to the EasyKanban system.
     */
    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks would you like to enter?"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            String description;

            // Ensure task description is valid
            do {
                description = JOptionPane.showInputDialog("Enter Task Description (max 50 characters):");
                if (!Task.checkTaskDescription(description)) {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                } else {
                    // Confirm successful entry of the task description to the user.
                    JOptionPane.showMessageDialog(null, "Task successfully captured.");
                }
            } while (!Task.checkTaskDescription(description));

            String developerName = JOptionPane.showInputDialog("Enter Developer's First and Last Name:");
            int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (in hours):"));

            String[] statusOptions = {"To Do", "Done", "Doing"};
            String status = (String) JOptionPane.showInputDialog(null, "Select Task Status:", "Task Status", JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            Task task = new Task(taskName, tasks.size(), description, developerName, duration, status);
            tasks.add(task);
            totalHours += duration;

            // Display task details using JDialog
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }
            // At the end of the task addition process, inform the user of the total hours accumulated across all tasks.
            JOptionPane.showMessageDialog(null, "Total Hours across all tasks: " + totalHours);
    }

    /**
     * Displays a report of all tasks in the system.
     */
    private static void showReport() {
        StringBuilder report = new StringBuilder("Task Report:\n\n");
        for (Task task : tasks) {
            report.append(task.printTaskDetails()).append("\n------------------\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    /**
     * Displays tasks with 'Done' status.
     */
    private static void displayTaskWithDoneStatus() {
        StringBuilder doneTasks = new StringBuilder("Tasks With Done Status:\n");
        for (Task task : tasks) {
            if ("Done".equalsIgnoreCase(task.getTaskStatus())) {
                doneTasks.append("Developer: ").append(task.getDeveloperName())
                         .append("\nTask Name: ").append(task.getTaskName())
                         .append("\nDuration: ").append(task.getTaskDuration()).append(" hours\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, doneTasks.toString());
    }

    /**
     * Displays the task with the longest duration.
     */
    private static void displayTaskWithLongestDuration() {
        Task longestTask = tasks.stream().max((t1, t2) -> Integer.compare(t1.getTaskDuration(), t2.getTaskDuration())).orElse(null);
        if (longestTask != null) {
            JOptionPane.showMessageDialog(null, "Task with Longest Duration:\nDeveloper: " + longestTask.getDeveloperName() +
                    "\nDuration: " + longestTask.getTaskDuration() + " hours");
        }
    }

    /**
     * Searches for a task by its name and displays the details if found.
     */
    private static void searchTaskByName() {
        String taskNameSearch = JOptionPane.showInputDialog("Enter Task Name to search:");
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskNameSearch)) {
                JOptionPane.showMessageDialog(null, "Task Found:\n" + task.printTaskDetails());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    /**
     * Searches for tasks assigned to a specific developer and displays them.
     */
    private static void searchTaskByDeveloper() {
        String developerNameSearch = JOptionPane.showInputDialog("Enter Developer's Name to search for their tasks:");
        StringBuilder developerTasks = new StringBuilder("Tasks assigned to " + developerNameSearch + ":\n\n");
        boolean found = false;

        for (Task task : tasks) {
            if (task.getDeveloperName().equalsIgnoreCase(developerNameSearch)) {
                developerTasks.append(task.printTaskDetails()).append("\n------------------\n");
                found = true;
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(null, developerTasks.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found for developer: " + developerNameSearch);
        }
    }

    /**
     * Deletes a task by its name.
     */
    private static void deleteTaskByName() {
        String taskNameDelete = JOptionPane.showInputDialog("Enter Task Name to delete:");
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskNameDelete)) {
                tasks.remove(task);
                JOptionPane.showMessageDialog(null, "Task " + taskNameDelete + " deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }
}


