package pochi.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private static final List<String> COMMANDS_WITH_ZERO_ARGUMENT = List.of("bye", "list");
    private static final List<String> COMMANDS_WITH_ONE_ARGUMENT =
        List.of("mark", "unmark", "delete", "find");

    private static final List<String> TASKS = List.of("todo", "deadline", "event");
    private static final List<List<String>> SEPARATORS_FOR_TASKS =
        List.of(List.of(), List.of("/by"), List.of("/from", "/to"));

    private static final String DEFAULT_FALSE = "false";

    private static List<String> parseDescriptions(List<String> descriptions, List<String> separators)
            throws MissingArgumentException {

        List<String> parsedDescriptions = new ArrayList<>();
        String curr = "";
        for (int i = 1, j = 0; i < descriptions.size(); i++) {
            if (j < separators.size() && descriptions.get(i).equals(separators.get(j))) {
                parsedDescriptions.add(curr);
                curr = "";
                j++;
                continue;
            }
            if (!curr.isEmpty()) {
                curr += " ";
            }
            curr += descriptions.get(i);
        }
        parsedDescriptions.add(curr);

        int numberOfPartitions = separators.size() + 1;
        if (parsedDescriptions.size() != numberOfPartitions) {
            throw new MissingArgumentException();
        }

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

    private static List<String> createTaskDescriptions(String query, List<String> parsedDescriptions)
            throws InvalidDateException {

        List<String> result = new ArrayList<>();
        result.add(query);
        result.add(parsedDescriptions.get(0));
        result.add(DEFAULT_FALSE);
        for (int i = 1; i < parsedDescriptions.size(); i++) {
            result.add(Parser.convertToLocalDateTime(parsedDescriptions.get(i)).toString());
        }
        return result;
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

        String query = commands.get(0);

        if (COMMANDS_WITH_ZERO_ARGUMENT.stream().anyMatch(query::equals)) {
            return List.of(query);
        }
        if (COMMANDS_WITH_ONE_ARGUMENT.stream().anyMatch(query::equals)) {
            int numberOfQuery = 1;
            int numberOfArgument = 1;
            if (commands.size() < numberOfQuery + numberOfArgument) {
                throw new MissingArgumentException();
            }
            return List.of(query, commands.get(1));
        }
        if (TASKS.stream().anyMatch(query::equals)) {
            int type = 0;
            for (int i = 0; i < TASKS.size(); i++) {
                if (TASKS.get(i).equals(query)) {
                    type = i;
                }
            }
            return Parser.createTaskDescriptions(query,
                Parser.parseDescriptions(commands, SEPARATORS_FOR_TASKS.get(type)));
        }
        throw new InvalidCommandException();
    }
}
