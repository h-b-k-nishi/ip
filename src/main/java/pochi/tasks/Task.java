package pochi.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import pochi.exceptions.*;

/**
 * A class that represents a task.
 * 
 * @author Hibiki Nishiwaki
 */
public class Task {
    private final String description;
    private boolean isCompleted;

    /**
     * The constructor of task.
     * 
     * @param description The description of task.
     */
    public Task(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Creates an instance of Task (or its subclass) based on the given information.
     * 
     * @param descriptions The descriptions of instance.
     * @return The newly created instance of Task.
     */
    public static Task of(List<String> descriptions) throws TaskCreationException {
        Task res;
        if (descriptions.isEmpty()) {
            throw new MissingArgumentException();
        }
        if (descriptions.get(0).equals("todo")) {
            if (descriptions.size() < 3) {
                throw new MissingArgumentException();
            }
            res = new Todo(descriptions.get(1));
        } else if (descriptions.get(0).equals("deadline")) {
            if (descriptions.size() < 4) {
                throw new MissingArgumentException();
            }
            try {
                res = new Deadline(descriptions.get(1), LocalDateTime.parse(descriptions.get(3)));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else if (descriptions.get(0).equals("event")) {
            if (descriptions.size() < 5) {
                throw new MissingArgumentException();
            }
            try {
                res = new Event(descriptions.get(1), 
                LocalDateTime.parse(descriptions.get(3)), LocalDateTime.parse(descriptions.get(4)));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidCommandException();
        }
        res.isCompleted = Boolean.valueOf(descriptions.get(2));
        return res;
    }

    /**
     * Mark this task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Mark this task as uncompleted.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Return the string representation of this task.
     * 
     * @return The string consisting of the description of task.
     */
    @Override
    public String toString() {
        return (this.isCompleted ? "[X]" : "[ ]") + " " + this.description;
    }

    /**
     * Returns a short description of this task.
     * 
     * @return The string description
     */
    public String log() {
        return this.description + " | " + this.isCompleted;
    }
}
