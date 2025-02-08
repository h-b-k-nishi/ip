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
    private static final String INITIAL = "D";
    private static final String LOG_PREFIX = "deadline | ";

    /** The deadline of this deadline. */
    private final LocalDateTime deadline;

    /**
     * Constructs a new instance of Deadline task.
     *
     * @param description The description of task.
     * @param deadline The deadline of task.
     * @throws EmptyDescriptionException Thrown when the given description is empty.
     */
    public Deadline(String description, LocalDateTime deadline) throws EmptyDescriptionException {
        super(description);

        this.deadline = deadline;
    }

    /**
     * Returns the string representation of this Deadline task.
     *
     * @return The string consisting of the description of task.
     */
    @Override
    public String toString() {
        return "[" + Deadline.INITIAL + "] " + super.toString()
            + " (by: " + Ui.formatDateTime(this.deadline) + ")";
    }

    /**
     * Returns a short description of this task.
     *
     * @return The string description
     */
    @Override
    public String getLog() {
        return Deadline.LOG_PREFIX + super.getLog() + " | " + this.deadline;
    }
}
