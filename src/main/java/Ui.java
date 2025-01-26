import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * A class deals with interactions with the user.
 * 
 * @author Hibiki Nishiwaki
 */
public class Ui {
    private static final String GREET = "Hello! I'm Pochi.\n"
        + "What can I do for you?\n",
        FAREWELL = "Bye. Hope to see you again soon!\n";
    private final Scanner sc;

    /**
     * The constructor of Ui, which initializes the scanner 
     * from standard input and greets to the user.
     * 
     * @param pochi The chatbot interacting with the user.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        System.out.println(GREET);
    }
    
    /**
     * Terminates the UI.
     */
    public void exit() {
        System.out.println(FAREWELL);
        sc.close();
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
    public void printList(List<String> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * Read a new line of user input.
     * 
     * @return The list of string representing the input, separated by space.
     */
    public List<String> readInput() {
        String input = sc.nextLine();
        return List.of(input.split(" "));
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
