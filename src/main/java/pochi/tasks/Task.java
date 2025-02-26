package pochi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Stream;

import pochi.exceptions.EmptyDescriptionException;
import pochi.exceptions.InvalidCommandException;
import pochi.exceptions.InvalidDateException;
import pochi.exceptions.MissingArgumentException;
import pochi.exceptions.TaskCreationException;

/**
 * A class that represents a task.
 *
 * @author Hibiki Nishiwaki
 */
public class Task {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private static final int NUMBER_OF_ARGUMENTS_FOR_TODO = 3;
    private static final int NUMBER_OF_ARGUMENTS_FOR_DEADLINE = 4;
    private static final int NUMBER_OF_ARGUMENTS_FOR_EVENT = 5;

    private static final String COMPLETE = "[X]";
    private static final String INCOMPLETE = "[ ]";

    private final String description;
    private boolean isCompleted;

    /**
     * Constructs a new instance of Task.
     *
     * @param description The description of task.
     * @throws EmptyDescriptionException Thrown when the given description is empty.
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
     * @throws TaskCreationException Thrown when some error occurs during the creation of task.
     */
    public static Task createTask(List<String> descriptions) throws TaskCreationException {

        assert !descriptions.isEmpty() : "The given descriptions cannot be empty!";

        if (descriptions.isEmpty()) {
            throw new MissingArgumentException();
        }

        Task res;

        String type = descriptions.get(0);

        assert Stream.of("todo", "deadline", "event").anyMatch(descriptions.get(0)::equals);

        if (type.equals(TODO)) {
            if (descriptions.size() < NUMBER_OF_ARGUMENTS_FOR_TODO) {
                throw new MissingArgumentException();
            }

            res = new Todo(descriptions.get(1));
        } else if (type.equals(DEADLINE)) {
            if (descriptions.size() < NUMBER_OF_ARGUMENTS_FOR_DEADLINE) {
                throw new MissingArgumentException();
            }
            try {
                res = new Deadline(descriptions.get(1),
                        LocalDateTime.parse(descriptions.get(3)));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else if (type.equals(EVENT)) {
            if (descriptions.size() < NUMBER_OF_ARGUMENTS_FOR_EVENT) {
                throw new MissingArgumentException();
            }
            try {
                res = new Event(descriptions.get(1),
                        LocalDateTime.parse(descriptions.get(3)),
                                LocalDateTime.parse(descriptions.get(4)));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidCommandException();
        }

        res.isCompleted = Boolean.valueOf(descriptions.get(2));

        return res;
    }

    /**
     * Marks this task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Marks this task as uncompleted.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return The string consisting of the description of task.
     */
    @Override
    public String toString() {
        return (this.isCompleted ? COMPLETE : INCOMPLETE) + " " + this.description;
    }

    /**
     * Returns a short description of this task.
     *
     * @return The string description
     */
    public String getLog() {
        return this.description + " | " + this.isCompleted;
    }

    /**
     * Checks if the given keyword is contained in task description.
     *
     * @param keyword The keyword being examined.
     * @return True if the keyword is contained, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}
