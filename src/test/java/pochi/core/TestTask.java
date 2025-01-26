package pochi.tasks;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import pochi.exceptions.*;

/*
 * Since it is guranteed that 
 */
public class TestTask {
    @Test
    public void missingArgumentTest() {
        try{
            Task task = Task.of(List.of("todo", "return book"));
            assertEquals(0,1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Some arguments (/by, /from, /to, or the index of task) are missing!!");
        }
    }
    @Test
    public void invalidDateTimeTest() {
        try{
            Task task = Task.of(List.of("deadline", "hw2", "2025-02-03 09:10", "false"));
            assertEquals(0,1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Invalid format of date! The format has to be yyyy-mm-dd hh:mm.");
        }
    }
    // Java does not support the leap year.
    @Test
    public void leapYearTest() {
        try{
            Task task = Task.of(List.of("deadline", "hw2", "2024-02-29 09:10", "false"));
            assertEquals(0,1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Invalid format of date! The format has to be yyyy-mm-dd hh:mm.");
        }
    }
    @Test
    public void emptyNameTest() {
        try{
            Task task = Task.of(List.of("todo", "", "false"));
            assertEquals(0,1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Your task description (i.e. task name) is empty!");
        }
    }
    @Test
    public void eventWorkingTest() {
        try{
            Task task = Task.of(List.of("event", "dinner", "2024/02/28 19:00", "2024/02/28 20:00", "FFF"));
            assertEquals(task.toString(), "[E] [ ] dinner (from: Feb 28 2024 19:00 to: Feb 28 2024 20:00)");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Invalid format of date! The format has to be yyyy-mm-dd hh:mm.");
        }
    }
}
