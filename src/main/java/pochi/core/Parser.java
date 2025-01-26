package pochi.core;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import pochi.exceptions.*;

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

    /**
     * Converts a string representation of date and time to LocalDateTime.
     * 
     * @param dateAndTime The string representation of date and time.
     * @return The converted instance of LocalDateTime.
     * @throws InvalidDateException Thrown when the format is invalid.
     */
    private static LocalDateTime toLocalDateTime(String dateAndTime) throws InvalidDateException {
        try {
            return LocalDateTime.parse(dateAndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
    
    /**
     * Parses a command given by the user.
     * 
     * @param command The string representation of string.
     * @return A list of string consisting of the parsed strings.
     * @throws CommandException Thrown when the given command has an invalid format.
     */
    public static List<String> parse(String command) throws CommandException {
        List<String> info = List.of(command.split(" "));
        if (info.isEmpty()) {
            throw new EmptyCommandException();
        }
        List<String> res = new ArrayList<>();
        res.add(info.get(0));
        if (info.get(0).equals("bye")
        || info.get(0).equals("list")){
            // do nothing
        } else if(info.get(0).equals("mark") 
        || info.get(0).equals("unmark")
        || info.get(0).equals("delete")) {
            if (info.size() < 2) {
                throw new MissingArgumentException();
            }
            res.add(info.get(1));
        } else if (info.get(0).equals("todo")) {
            List<String> parsed = parseDescriptions(info, List.of());
            res.add(parsed.get(0));
            res.add("false");
        } else if (info.get(0).equals("deadline")) {
            List<String> parsed = parseDescriptions(info, List.of("/by"));
            if (parsed.size() < 2) {
                throw new MissingArgumentException();
            }
            res.add(parsed.get(0));
            res.add("false");
            res.add(Parser.toLocalDateTime(parsed.get(1)).toString());
        } else if (info.get(0).equals("event")) {
            List<String> parsed = parseDescriptions(info, List.of("/from", "/to"));
            if (parsed.size() < 3) {
                throw new MissingArgumentException();
            }
            res.add(parsed.get(0));
            res.add("false");
            res.add(Parser.toLocalDateTime(parsed.get(1)).toString());
            res.add(Parser.toLocalDateTime(parsed.get(2)).toString());
        } else {
            throw new InvalidCommandException();
        }
        return res;
    }
}
