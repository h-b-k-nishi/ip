package pochi.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * A class that tests Task.
 *
 * @author Hibiki Nishiwaki
 */
public class TaskTest {
    /**
     * Performs a test on a case where the input list is shorter than required.
     */
    @Test
    public void missingArgumentTest() {
        try {
            Task task = Task.createTask(List.of("todo", "return book"));

            assertEquals(0, 1);
        } catch (Exception e) {
            assertEquals(e.getMessage(),
                    "Some arguments (/by, /from, /to, or the index of task) are missing!!");
        }
    }
    /**
     * Performs a test on a case where the input format is slightly different from the required.
     */
    @Test
    public void invalidDateTimeTest() {
        try {
            Task task = Task.createTask(List.of("deadline", "hw2", "2025-02-03 09:10", "false"));

            assertEquals(0, 1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Invalid format of date! The format has to be yyyy-mm-dd hh:mm.");
        }
    }
    /**
     * Performs a test on a case where the input contains a leap year date, which is not supported by Java.
     */
    @Test
    public void leapYearTest() {
        try {
            Task task = Task.createTask(List.of("deadline", "hw2", "2024-02-29 09:10", "false"));

            assertEquals(0, 1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Invalid format of date! The format has to be yyyy-mm-dd hh:mm.");
        }
    }
    /**
     * Performs a test on a case where the input includes an empty string as the description of task.
     */
    @Test
    public void emptyNameTest() {
        try {
            Task task = Task.createTask(List.of("todo", "", "false"));

            assertEquals(0, 1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Your task description (i.e. task name) is empty!");
        }
    }
    /**
     * Checks if the method works with a normal input representing an event.
     */
    @Test
    public void eventWorkingTest() {
        try {
            Task task = Task.createTask(
                    List.of("event", "dinner", "2024/02/28 19:00", "2024/02/28 20:00", "FFF"));

            assertEquals(task.toString(), "[E] [ ] dinner (from: Feb 28 2024 19:00 to: Feb 28 2024 20:00)");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Invalid format of date! The format has to be yyyy-mm-dd hh:mm.");
        }
    }
}
