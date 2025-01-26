import java.util.List;

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
        if (descriptions.get(0).equals("todo")) {
            return new Todo(descriptions.get(1));
        } else if (descriptions.get(0).equals("deadline")) {
            return new Deadline(descriptions.get(1), Parser.toLocalDateTime(descriptions.get(2)));
        } else if (descriptions.get(0).equals("event")) {
            return new Event(descriptions.get(1), 
            Parser.toLocalDateTime(descriptions.get(2)), Parser.toLocalDateTime(descriptions.get(3)));
        } else {
            throw new InvalidCommandException();
        }
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
        return this.isCompleted + " | " + this.description;
    }
}
