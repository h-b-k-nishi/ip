package pochi.core;

import java.io.IOException;
import java.util.List;

import pochi.exceptions.CommandException;
import pochi.exceptions.EmptyCommandException;
import pochi.tasks.Task;

/**
 * A class represents a chatbot Pochi.
 *
 * @author Hibiki Nishiwaki
 */
public class Pochi {

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
        String res = "";
        if (commands.get(0).equals("list")) {
            res += tasks.getStatus() + "\n";
        } else if (commands.get(0).equals("find")) {
            List<String> results = tasks.findTask(commands.get(1));

            res += results.size() + " tasks are found:\n";
            for (int i = 0; i < results.size(); i++) {
                res += results.get(i).toString() + "\n";
            }
        } else if (commands.get(0).equals("mark")) {
            int index = Integer.parseInt(commands.get(1));
            Task marked = tasks.markTask(index);

            res += "Wonderful! I've marked this task as completed:\n";
            res += marked.toString() + "\n";
        } else if (commands.get(0).equals("unmark")) {
            int index = Integer.parseInt(commands.get(1));
            Task unmarked = tasks.unmarkTask(index);

            res += "Okay, I've marked this task as incompleted:\n";
            res += unmarked.toString() + "\n";
        } else if (commands.get(0).equals("delete")) {
            int index = Integer.parseInt(commands.get(1));
            Task removed = tasks.deleteTask(index);

            res += "Noted. I've removed this task:\n";
            res += removed.toString() + "\n";
        } else {
            Task added = tasks.addTask(Task.createTask(commands));

            res += "Noted. I've added this task: \n";
            res += added.toString() + "\n";
        }
        return res;
    }

    /**
     * Greets to the user.
     *
     * @return The greeting message.
     */
    public String greet() {
        return "Hello! I'm Pochi.\n" + "What can I do for you?\n";
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
                tasks.addTask(Task.createTask(List.of(logs.get(i).split(" \\| "))));
            }

            if (!tasks.isEmpty()) {
                res += "Sucessfully loaded the previous log!\n";
            }
        } catch (Exception e) {
            res += "Oops! Some error occurred when loading the log from the previous session.\n";
            res += "The history of previous session is lost. I am very sorry...\n";
        } finally {
            res += "Here is the list of current tasks:\n";
            res += tasks.getStatus() + "\n";
        }
        return res;
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

            if (parsedCommands.get(0).equals("bye")) {
                return "Bye. Hope to see you again soon!\n";
            }

            res += processCommand(parsedCommands);
            res += "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.\n";

            storage.createLog(tasks.getLog());
        } catch (EmptyCommandException e) {
            res += "Oops! Some error occurred!\n";
            res += "Your command is empty. Please enter something!!\n";
        } catch (CommandException e) {
            res += "Oops! Some error occurred!\n";
            res += e.getMessage();
        } catch (IOException e) {
            res += "Oops! Some error occurred during the creation of log file.\n";
            res += "Please note that the current status of your tasks is not saved, sorry...\n";
        }
        return res;
    }
}
