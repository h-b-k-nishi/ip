package pochi.tasks;
import java.time.LocalDateTime;
import pochi.core.Ui;
import pochi.exceptions.EmptyDescriptionException;
/**
 * A class that represents a task with deadline.
 * 
 * @author Hibiki Nishiwaki
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;
    /**
     * The constructor of Deadline task.
     * 
     * @param description The description of task.
     * @param deadline The deadline of task.
     */
    public Deadline(String description, LocalDateTime deadline) throws EmptyDescriptionException {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Return the string representation of this Deadline task.
     * 
     * @return The string consisting of the description of task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + Ui.formatDateTime(this.deadline) + ")";
    }

    /**
     * Returns a short description of this task.
     * 
     * @return The string description
     */
    @Override
    public String getLog() {
        return "deadline | " + super.getLog() + " | " + this.deadline;
    }
}
