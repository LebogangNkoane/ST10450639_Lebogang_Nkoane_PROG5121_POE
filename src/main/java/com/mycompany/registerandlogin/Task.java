/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registerandlogin;

/**
 *
 * @author RC_Student_lab Lebogang Nkoane
 */
public class Task {
    // The Task class encapsulates the details of an individual task, including attributes such as the task name, task number, description, developer's name, duration, and status. 
    // This structure facilitates organized management of tasks while providing methods to validate task descriptions, generate unique task IDs, and format task details for display purposes.

    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerName;
    private int taskDuration;
    private String taskStatus;
    private String taskID;

    // A Constructor that initializes a new task with all relevant details, including the creation of a unique task ID based on the task's attributes.
    public Task(String taskName, int taskNumber, String taskDescription, String developerName, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerName = developerName;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID(); // This Calls the method to generate a unique task ID for this task.
    }

    // This Checks if the task description adheres to the maximum character limit, returning true if valid or false if it exceeds the limit.
    public static boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    // This Generates a unique task ID by combining initials of the task name and the developer's name with the task number to ensure each task can be distinctly identified.
    public String createTaskID() {
        String taskInitials = taskName.substring(0, 2).toUpperCase();
        String developerEnd = developerName.substring(developerName.length() - 3).toUpperCase();
        return taskInitials + ":" + taskNumber + ":" + developerEnd; // Format: "TA:1:DEV"
    }

    // This Formats and returns a detailed string representation of the task, including all relevant attributes for easy readability.
    public String printTaskDetails() {
        return String.format(""" 
                             Task Status: %s
                             Developer Details: %s
                             Task Number: %d
                             Task Name: %s
                             Task Description: %s
                             Task ID: %s
                             Task Duration: %d hours
                             """, taskStatus, developerName, taskNumber, taskName, taskDescription, taskID, taskDuration);
    }

    // This Getter method that returns the duration of the task, allowing other classes to access this information as needed.
    public int getTaskDuration() {
        return taskDuration;
    }
}
