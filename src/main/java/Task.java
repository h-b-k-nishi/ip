/**
 * A class that represents a task.
 * 
 * @author Hibiki Nishiwaki
 */
public class Task {
    private final String name;

    /**
     * The constructor of task.
     * 
     * @param name The name of task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Return the string representation of this task.
     * 
     * @return The string consisting of the name of task.
     */
    public String toString() {
        return this.name;
    }
}
