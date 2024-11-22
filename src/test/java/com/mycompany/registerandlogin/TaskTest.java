/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registerandlogin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 *
 * @author RC_Student_lab
 */
public class TaskTest {

    // Test data setup
    ArrayList<Task> tasks = new ArrayList<>();

    public TaskTest() {
        // Populate the tasks list with provided test data
        tasks.add(new Task("Create Login", 0, "Login functionality for the app", "Mike Smith", 5, "To Do"));
        tasks.add(new Task("Create Add Features", 1, "Adding new features to the app", "Edward Harrison", 8, "Doing"));
        tasks.add(new Task("Create Reports", 2, "Generate reports for analysis", "Samantha Paulson", 2, "Done"));
        tasks.add(new Task("Add Arrays", 3, "Implement array functionality", "Glenda Oberholzer", 11, "To Do"));
    }

    @Test
    public void testTaskDescriptionValidation() {
        assertTrue(Task.checkTaskDescription("Short description")); // Valid
        assertFalse(Task.checkTaskDescription("This description is way too long to be valid and should fail the validation")); // Invalid
    }

    @Test
    public void testTaskIDGeneration() {
        Task task = new Task("Create Login", 0, "Login functionality", "Mike Smith", 5, "To Do");
        String expectedTaskID = "CR:0:ITH";
        assertEquals(expectedTaskID, task.createTaskID());
    }

    @Test
    public void testTaskStatusSearch() {
        ArrayList<Task> doneTasks = new ArrayList<>();
        for (Task task : tasks) {
            if ("Done".equalsIgnoreCase(task.getTaskStatus())) {
                doneTasks.add(task);
            }
        }
        assertEquals(1, doneTasks.size());
        assertEquals("Create Reports", doneTasks.get(0).getTaskName());
    }

    @Test
    public void testDeveloperTaskSearch() {
        String developerName = "Mike Smith";
        ArrayList<Task> developerTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDeveloperName().equalsIgnoreCase(developerName)) {
                developerTasks.add(task);
            }
        }
        assertEquals(1, developerTasks.size());
        assertEquals("Create Login", developerTasks.get(0).getTaskName());
    }

    @Test
    public void testLongestTaskDuration() {
        Task longestTask = tasks.stream().max((t1, t2) -> Integer.compare(t1.getTaskDuration(), t2.getTaskDuration())).orElse(null);
        assertNotNull(longestTask);
        assertEquals("Add Arrays", longestTask.getTaskName());
        assertEquals(11, longestTask.getTaskDuration());
    }

    @Test
    public void testTaskDeletion() {
        String taskNameToDelete = "Create Login";
        tasks.removeIf(task -> task.getTaskName().equalsIgnoreCase(taskNameToDelete));

        boolean taskExists = tasks.stream().anyMatch(task -> task.getTaskName().equalsIgnoreCase(taskNameToDelete));
        assertFalse(taskExists); // Ensure task is removed
    }
}
 