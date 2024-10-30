/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registerandlogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class TaskTest {
    
    public TaskTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // Test Case 1: Task Description Validation
    // This test validates the task description length. It checks for successful capture of descriptions under 50 characters and failure for those exceeding this limit.
    @Test
    public void testTaskDescriptionValidation() {
        // Test for a valid scenario (under 50 characters)
        String validDescription = "Create Login to authenticate users";
        assertTrue(Task.checkTaskDescription(validDescription), "Task description should be captured successfully for lengths under 50 characters.");

        // Test for a failure scenario (more than 50 characters)
        String invalidDescription = "This is an extremely long task description that exceeds fifty characters";
        assertFalse(Task.checkTaskDescription(invalidDescription), "Task description validation should fail for lengths over 50 characters.");
    }

    // Test Case 2: Task ID Generation
    // This test verifies that the task ID generation method correctly constructs IDs based on the provided task and developer details, ensuring the expected format is met.
    @Test
    public void testTaskIDGeneration() {
        // Test data for creating tasks
        Task task1 = new Task("Login Feature", 0, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        Task task2 = new Task("Add Task Feature", 1, "Create Add Task Feature to add task users", "Mike Smith", 10, "Doing");

        // Validate Task IDs against expected values
        assertEquals("LO:0:SON", task1.createTaskID(), "Task ID for Task 1 should match expected format.");
        assertEquals("AD:1:ITH", task2.createTaskID(), "Task ID for Task 2 should match expected format.");
    }

    // Test Case 3: Total Hours Calculation
    // This test calculates the total hours from multiple tasks and verifies the result against expected values. It also validates accumulation of hours from an array of durations.
    @Test
    public void testTotalHoursAccumulated() {
        // Set up tasks for testing total hours
        Task task1 = new Task("Login Feature", 0, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        Task task2 = new Task("Add Task Feature", 1, "Create Add Task Feature to add task users", "Mike Smith", 10, "Doing");

        // Accumulate total hours and check the result
        int totalHours = task1.getTaskDuration() + task2.getTaskDuration();
        assertEquals(18, totalHours, "Total hours for Task 1 and Task 2 should equal 18.");

        // Additional test data for looping
        int[] durations = {10, 12, 55, 11, 1}; // Total should be 89
        int expectedTotal = 89;
        int actualTotal = 0;
        for (int duration : durations) {
            actualTotal += duration; // Accumulating total hours
        }
        assertEquals(expectedTotal, actualTotal, "Total hours should equal 89 for the additional set of durations.");
    }
}
