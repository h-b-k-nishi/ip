package pochi.core;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import pochi.exceptions.CommandException;
import pochi.exceptions.EmptyCommandException;
import pochi.exceptions.InvalidCommandException;
import pochi.exceptions.InvalidDateException;
import pochi.exceptions.MissingArgumentException;

/**
 * A class parses commands from the user.
 * 
 * @author Hibiki Nishiwaki
 */
public class Parser {

    private static List<String> parseDescriptions(List<String> descriptions, List<String> separators) {
        List<String> parsedDescriptions = new ArrayList<>();
        String curr = "";
        for(int i = 1, j = 0; i < descriptions.size(); i++) {
            if (j < separators.size() && descriptions.get(i).equals(separators.get(j))) {
                parsedDescriptions.add(curr);
                curr = "";
                j++;
            } else {
                if (!curr.isEmpty()) {
                    curr += " ";
                }
                curr += descriptions.get(i);
            }
        }
        parsedDescriptions.add(curr);
        return parsedDescriptions;
    }

    /**
     * Converts a string representation of date and time to LocalDateTime.
     * 
     * @param dateAndTime The string representation of date and time.
     * @return The converted instance of LocalDateTime.
     * @throws InvalidDateException Thrown when the format is invalid.
     */
    private static LocalDateTime convertToLocalDateTime(String dateAndTime) throws InvalidDateException {
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
    public static List<String> parseCommand(String command) throws CommandException {
        List<String> commands = List.of(command.split(" "));

        if (commands.isEmpty()) {
            throw new EmptyCommandException();
        }

        List<String> results = new ArrayList<>();

        results.add(commands.get(0));

        if (commands.get(0).equals("bye") || commands.get(0).equals("list")){
            // do nothing
        } else if(commands.get(0).equals("mark") 
                || commands.get(0).equals("unmark")
                        || commands.get(0).equals("delete")
                                || commands.get(0).equals("find")) {
            if (commands.size() < 2) {
                throw new MissingArgumentException();
            }

            results.add(commands.get(1));
        } else if (commands.get(0).equals("todo")) {
            List<String> parsedDescriptions = parseDescriptions(commands, List.of());

            results.add(parsedDescriptions.get(0));
            results.add("false");
        } else if (commands.get(0).equals("deadline")) {
            List<String> parsedDescriptions = parseDescriptions(commands, List.of("/by"));

            if (parsedDescriptions.size() < 2) {
                throw new MissingArgumentException();
            }

            results.add(parsedDescriptions.get(0));
            results.add("false");
            results.add(Parser.convertToLocalDateTime(parsedDescriptions.get(1)).toString());
        } else if (commands.get(0).equals("event")) {
            List<String> parsedDescriptions = parseDescriptions(commands, List.of("/from", "/to"));

            if (parsedDescriptions.size() < 3) {
                throw new MissingArgumentException();
            }

            results.add(parsedDescriptions.get(0));
            results.add("false");
            results.add(Parser.convertToLocalDateTime(parsedDescriptions.get(1)).toString());
            results.add(Parser.convertToLocalDateTime(parsedDescriptions.get(2)).toString());
        } else {
            throw new InvalidCommandException();
        }
        return results;
    }
}
