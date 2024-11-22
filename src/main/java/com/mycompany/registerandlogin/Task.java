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
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerName;
    private int taskDuration;
    private String taskStatus;
    private String taskID;

    // Constructor initializes the task attributes and generates a unique task ID
    public Task(String taskName, int taskNumber, String taskDescription, String developerName, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerName = developerName;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
    }

    // Method to check if the task description is within 50 characters
    public static boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    // Generates a unique Task ID using the task name and developer initials
    public String createTaskID() {
        String taskInitials = taskName.substring(0, 2).toUpperCase();  // First two characters of task name
        String developerEnd = developerName.substring(developerName.length() - 3).toUpperCase();  // Last three characters of developer name
        return taskInitials + ":" + taskNumber + ":" + developerEnd;
    }

    // Formats task details into a readable string for display
    public String printTaskDetails() {
        return String.format("""
                             Task Status: %s
                             Developer: %s
                             Task Number: %d
                             Task Name: %s
                             Task Description: %s
                             Task ID: %s
                             Task Duration: %d hours
                             """, taskStatus, developerName, taskNumber, taskName, taskDescription, taskID, taskDuration);
    }

    // Getters for retrieving task details
    public int getTaskDuration() {
        return taskDuration;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public String getDeveloperName() {
        return developerName;
    }
}


