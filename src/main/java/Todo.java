/**
 * A class that represents a Todo task.
 * 
 * @author Hibiki Nishiwaki
 */
public class Todo extends Task {
    /**
     * The constructor of Todo task.
     * 
     * @param description The description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return the string representation of this Todo task.
     * 
     * @return The string consisting of the description of task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
