package pochi.core;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Scanner;

/**
 * A class deals with interactions with the user.
 * 
 * @author Hibiki Nishiwaki
 */
public class Ui {
    /** The string representation of greet message. */
    private static final String GREET = "Hello! I'm Pochi.\n" + "What can I do for you?\n";

    /** The string representation of farewell message. */
    private static final String FAREWELL = "Bye. Hope to see you again soon!";

    /** A scanner which will be used for the interaction with the user through standard I/O. */
    private final Scanner scanner;

    /**
     * Constructs a new instance of Ui, 
     * initializaing the scannerfrom standard input and greeting to the user.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);

        System.out.println(GREET);
    }
    
    /**
     * Terminates the UI.
     */
    public void exit() {
        System.out.println(FAREWELL);

        scanner.close();
    }

    /**
     * Prints the current status of task list.
     * 
     * @param numberOfTasks The current number of tasks in the list.
     */
    public void printStatus(int numberOfTasks) {
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Prints a list of strings, representing tasks in the list.
     * 
     * @param list The list of string representations.
     */
    public void printList(List<String> descriptions) {
        for(int i = 0; i < descriptions.size(); i++) {
            System.out.println(descriptions.get(i));
        }
    }

    /**
     * Prints an error message.
     * 
     * @param message The message expressing the error.
     */
    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Changes a line by printing "\n".
     */
    public void changeLine() {
        System.out.println();
    }

    /**
     * Notifies the user of the addition of new task.
     * 
     * @param taskDescription A string representation of newly added task.
     */
    public void addTask(String taskDescription) {
        System.out.println("added: " + taskDescription);
    }

    /**
     * Notifies the user of the removal of task.
     * 
     * @param taskDescription A string representation of removed task.
     */
    public void removeTask(String taskDescription) {
        System.out.println("Noted. I've removed this task:\n" + taskDescription);
    }

    /**
     * Notifies the user of the marking of task.
     * 
     * @param taskDescription A string representation of marked task.
     */
    public void markTask(String taskDescription) {
        System.out.println("Wonderful! I've marked this task as completed:\n" + taskDescription);
    }

    /**
     * Notifies the user of the unmarking of task.
     * 
     * @param taskDescription A string representation of unmarked task.
     */
    public void unmarkTask(String taskDescription) {
        System.out.println("Okay, I've marked this task as incompleted:\n" + taskDescription);
    }

    /**
     * Notifies the user of the completion of loading the previous log.
     */
    public void completeLoad() {
        System.out.println("Sucessfully loaded the previous log!\n" 
                + "Here is the list of current tasks.");
    }

    /**
     * Notifies the user of the result of searching.
     * 
     * @param numberOfTasksFound The number of tasks meeting the condition.
     */
    public void notifySearchResult(int numberOfTasksFound) {
        System.out.println(numberOfTasksFound + " tasks are found:");
    }

    /**
     * Read a new line of user input.
     * 
     * @return The string representing the input.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Returns a desirable format of date.
     * 
     * @param dateAndTime A LocalDateTime converted to the desirable format.
     * @return A proper string representation of date.
     */
    public static String formatDateTime(LocalDateTime dateAndTime) {
        return dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"));
    }
}
