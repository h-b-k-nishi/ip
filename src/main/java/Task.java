import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

    private static List<String> parseDescriptions(List<String> descriptions, List<String> separator) {
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
        return parsed;
    }

    private static LocalDateTime toLocalDateTime(String data) throws InvalidDateException {
        try {
            return LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * A manufacturing method of Task (or its subclasses).
     * 
     * @param descriptions The description of Task being created.
     * @return An instance of Task.
     * @throws TaskCreationException This method may throw exceptions 
     * that can occur during a creation of Task instance.
     */
    public static Task of(List<String> descriptions) throws TaskCreationException {
        if (descriptions.get(0).equals("todo")) {
            List<String> parsed = parseDescriptions(descriptions, List.of());
            return new Todo(parsed.get(0));
        } else if (descriptions.get(0).equals("deadline")) {
            List<String> parsed = parseDescriptions(descriptions, List.of("/by"));
            if (parsed.size() < 2) {
                throw new MissingArgumentException();
            }
            return new Deadline(parsed.get(0), Task.toLocalDateTime(parsed.get(1)));
        } else if (descriptions.get(0).equals("event")) {
            List<String> parsed = parseDescriptions(descriptions, List.of("/from", "/to"));
            if (parsed.size() < 3) {
                throw new MissingArgumentException();
            }
            return new Event(parsed.get(0), 
            Task.toLocalDateTime(parsed.get(1)), Task.toLocalDateTime(parsed.get(2)));
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Returns a desirable format of date.
     */
    public static String formatDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"));
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
