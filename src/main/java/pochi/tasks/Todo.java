package pochi.tasks;

import pochi.exceptions.EmptyDescriptionException;

/**
 * A class that represents a Todo task.
 * 
 * @author Hibiki Nishiwaki
 */
public class Todo extends Task {
    /**
     * Constructs a new instance of Todo task.
     * 
     * @param description The description of task.
     * @throws EmptyDescriptionException Thrown when the given description is empty.
     */
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
    }

    /**
     * Returns the string representation of this Todo task.
     * 
     * @return The string consisting of the description of task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns a short description of this task.
     * 
     * @return The string description
     */
    @Override
    public String getLog() {
        return "todo | " + super.getLog();
    }
}
