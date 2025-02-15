package pochi.core;

import java.io.IOException;
import java.util.List;

import pochi.exceptions.CommandException;
import pochi.tasks.Task;

/**
 * A class represents a chatbot Pochi.
 *
 * @author Hibiki Nishiwaki
 */
public class Pochi {

    private static String LIST = "list";
    private static String FIND = "find";
    private static String MARK = "mark";
    private static String UNMARK = "unmark";
    private static String DELETE = "delete";
    private static String MARK_MESSAGE = "Wonderful! I've marked this task as completed:\n";
    private static String UNMARK_MESSAGE = "Okay, I've marked this task as incompleted:\n";
    private static String DELETE_MESSAGE = "Noted. I've removed this task:\n";
    private static String ADD_MESSAGE = "Noted. I've added this task: \n";

    private static String BYE = "bye";

    private static String GREET = "Hello! I'm Pochi.\n" + "What can I do for you?\n";
    private static String FAREWELL = "Bye. Hope to see you again soon!\n";

    private static String SEPARETOR_FOR_LOG = " \\| ";

    private static String ERROR = "Oops! Some error occurred!\n";
    private static String ERROR_LOG_CREATION = "Oops! Some error occurred during the creation of log file.\n"
        + "Please note that the current status of your tasks is not saved, sorry...\n";
    private static String ERROR_LOADING_LOG =
        "Oops! Some error occurred when loading the log from the previous session.\n"
        + "The history of previous session is lost. I am very sorry...\n";

    private static String SUCESS_LOADING_LOG = "Sucessfully loaded the previous log!\n";

    /** An instance of Storage handling file I/O regarding the logging. */
    private final Storage storage;

    /** An instance of TaskList maintaing the information of current tasks. */
    private final TaskList tasks;

    /**
     * Initializes the storage and task list.
     */
    public Pochi() {
        storage = new Storage();
        tasks = new TaskList();
    }

    private String processCommand(List<String> commands) throws CommandException {
        String query = commands.get(0);
        if (query.equals(LIST)) {
            return tasks.getStatus() + "\n";
        }
        if (query.equals(FIND)) {
            List<String> results = tasks.findTask(commands.get(1));
            int numberOfSearchResults = results.size();
            return numberOfSearchResults + " tasks are found:\n"
                + results.stream().reduce("", (x, y) -> x + y + "\n");
        }
        if (query.equals(MARK)) {
            int index = Integer.parseInt(commands.get(1));
            Task marked = tasks.markTask(index);
            return MARK_MESSAGE + marked.toString() + "\n";
        }
        if (query.equals(UNMARK)) {
            int index = Integer.parseInt(commands.get(1));
            Task unmarked = tasks.unmarkTask(index);
            return UNMARK_MESSAGE + unmarked.toString() + "\n";
        }
        if (query.equals(DELETE)) {
            int index = Integer.parseInt(commands.get(1));
            Task removed = tasks.deleteTask(index);
            return DELETE_MESSAGE + removed.toString() + "\n";
        }

        Task added = tasks.addTask(Task.createTask(commands));
        return ADD_MESSAGE + added.toString() + "\n";
    }

    /**
     * Greets to the user.
     *
     * @return The greeting message.
     */
    public String greet() {
        return GREET;
    }

    /**
     * Loads the log from previous session.
     *
     * @return The report of loading to the user.
     */
    public String processPreviousLog() {
        String res = "";
        try {
            List<String> logs = storage.readLog();

            for (int i = 0; i < logs.size(); i++) {
                tasks.addTask(Task.createTask(List.of(logs.get(i).split(SEPARETOR_FOR_LOG))));
            }

            if (!tasks.isEmpty()) {
                res += SUCESS_LOADING_LOG;
            }
        } catch (Exception e) {
            res += ERROR_LOADING_LOG;
        } finally {
            res += "Here is the list of current tasks:\n";
            res += tasks.getStatus() + "\n";
        }
        return res;
    }

    /**
     * Check if the input from the user indicates the end of dialog.
     *
     * @param userInput A text input from the user.
     * @return True if the userInput is equal to "bye"; false otherwise.
     */
    public boolean isConversationOver(String userInput) {
        try {
            List<String> parsedCommands = Parser.parseCommand(userInput);

            return parsedCommands.get(0).equals(BYE);
        } catch (CommandException e) {
            return false;
        }
    }

    /**
     * Responses to input commands from the user.
     *
     * @param userInput A text input from the user.
     * @return The response from Pochi.
     */
    public String getResponse(String userInput) {
        String res = "";
        try {
            List<String> parsedCommands = Parser.parseCommand(userInput);

            if (parsedCommands.get(0).equals(BYE)) {
                return FAREWELL;
            }

            res += processCommand(parsedCommands);
            res += "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.\n";

            storage.createLog(tasks.getLog());
        } catch (CommandException e) {
            res += ERROR;
            res += e.getMessage();
        } catch (IOException e) {
            res += ERROR_LOG_CREATION;
        }
        return res;
    }
}

