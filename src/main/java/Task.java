import java.util.ArrayList;
import java.util.Arrays;
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
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    private static List<String> parseDescriptions(List<String> descriptions, List<String> separator) {
        if (descriptions.isEmpty()) {
            return List.of();
        }
        List<String> parsed = new ArrayList<>();
        String curr = "";
        for(int i = 1, j = 0; i < descriptions.size(); i++) {
            if (j < separator.size() && descriptions.get(i).equals(separator.get(j))) {
                parsed.add(curr);
                curr = "";
                j++;
            } else {
                if (!curr.isEmpty()) {
                    curr += " ";
                }
                curr += descriptions.get(i);
            }
        }
        parsed.add(curr);
        // TODO: handle an empty string.
        return parsed;
    }

    public static Task of(List<String> descriptions) {
        // TODO: handle an empty string.
        if (descriptions.get(0).equals("todo")) {
            List<String> parsed = parseDescriptions(descriptions, List.of());
            return new Todo(parsed.get(0));
        } else if (descriptions.get(0).equals("deadline")) {
            List<String> parsed = parseDescriptions(descriptions, List.of("/by"));
            // TODO: handle parsed.size() < 2
            return new Deadline(parsed.get(0), parsed.get(1));
        } else if (descriptions.get(0).equals("event")) {
            List<String> parsed = parseDescriptions(descriptions, List.of("/from", "/to"));
            // TODO: handle parsed.size() < 3
            return new Event(parsed.get(0), parsed.get(1), parsed.get(2));
        } else {
            // TODO: throw an exception.
            return new Task("Error");
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
}
