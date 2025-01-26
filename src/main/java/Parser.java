import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class parses commands from the user.
 * 
 * @author Hibiki Nishiwaki
 */
public class Parser {
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
     * Creates a Task (or its subclasses) based on the given description.
     * 
     * @param descriptions The description of Task being created.
     * @return An instance of Task.
     * @throws TaskCreationException This method may throw exceptions 
     * that can occur during a creation of Task instance.
     */
    public static Task parseTask(List<String> descriptions) throws TaskCreationException {
        if (descriptions.get(0).equals("todo")) {
            List<String> parsed = parseDescriptions(descriptions, List.of());
            return new Todo(parsed.get(0));
        } else if (descriptions.get(0).equals("deadline")) {
            List<String> parsed = parseDescriptions(descriptions, List.of("/by"));
            if (parsed.size() < 2) {
                throw new MissingArgumentException();
            }
            return new Deadline(parsed.get(0), Parser.toLocalDateTime(parsed.get(1)));
        } else if (descriptions.get(0).equals("event")) {
            List<String> parsed = parseDescriptions(descriptions, List.of("/from", "/to"));
            if (parsed.size() < 3) {
                throw new MissingArgumentException();
            }
            return new Event(parsed.get(0), 
            Parser.toLocalDateTime(parsed.get(1)), Parser.toLocalDateTime(parsed.get(2)));
        } else {
            throw new InvalidCommandException();
        }
    }
}
