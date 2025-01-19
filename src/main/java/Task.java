/**
 * A class that represents a task.
 * 
 * @author Hibiki Nishiwaki
 */
public class Task {
    private final String description;

    /**
     * The constructor of task.
     * 
     * @param description The description of task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Return the string representation of this task.
     * 
     * @return The string consisting of the description of task.
     */
    public String toString() {
        return this.description;
    }
}
