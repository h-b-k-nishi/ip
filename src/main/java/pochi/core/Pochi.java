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
    /** An instance of Ui handling the interaction with the user. */
    private final Ui ui;

    /** An instance of Storage handling file I/O regarding the logging. */
    private final Storage storage;

    /** An instance of TaskList maintaing the information of current tasks. */
    private final TaskList tasks;

    private Pochi() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    private void processCommand(List<String> commands) throws CommandException {
        if (commands.get(0).equals("list")) {
            ui.printList(tasks.getStatus());
        } else if (commands.get(0).equals("find")) {
            List<String> results = tasks.findTask(commands.get(1));

            ui.notifySearchResult(results.size());
            ui.printList(results);
            ui.changeLine();
        } else if (commands.get(0).equals("mark")) {
            int index = Integer.parseInt(commands.get(1));

            Task marked = tasks.markTask(index);

        } else if (commands.get(0).equals("unmark")) {
            int index = Integer.parseInt(commands.get(1));

            Task unmarked = tasks.unmarkTask(index);

            ui.unmarkTask(unmarked.toString());
        } else if (commands.get(0).equals("delete")) {
            int index = Integer.parseInt(commands.get(1));

            Task removed = tasks.deleteTask(index);

            ui.removeTask(removed.toString());
        } else {
            Task added = tasks.addTask(Task.createTask(commands));

            ui.addTask(added.toString());
        }
    }

    private void processPreviousLog() {
        try {
            List<String> logs = storage.readLog();

            for (int i = 0; i < logs.size(); i++) {
                tasks.addTask(Task.createTask(List.of(logs.get(i).split(" \\| "))));
            }
            
            if (!tasks.isEmpty()) {
                ui.completeLoad();
                ui.printList(tasks.getStatus());
                ui.changeLine();
            }
        } catch (Exception e) {
            ui.printError(
                    "Oops! Some error occurred when loading the log from the previous session.");
            ui.printError("The history of previous session is lost. I am very sorry...");
            ui.changeLine();
        }
    }

    private void run() {
        processPreviousLog();

        while (true) {
            String command = ui.readInput();

            if (command.isEmpty()) {
                continue;
            }

            try {
                List<String> parsedCommands = Parser.parseCommand(command);

                if (parsedCommands.get(0).equals("bye")) {
                    ui.exit();
                    break;
                }

                processCommand(parsedCommands);

                ui.printStatus(tasks.getNumberOfTasks());

                storage.createLog(tasks.getLog());
            } catch (EmptyCommandException e) {
                // Do noting
            } catch (CommandException e) {
                ui.printError("Oops! Some error occurred!");
                ui.printError(e.toString());
            } catch (IOException e) {
                ui.printError("Oops! Some error occurred during the creation of log file.");
                ui.printError(
                        "Please note that the current status of tasks is not saved, sorry...");
            }finally {
                ui.changeLine();
            }
        }
    }
    public static void main(String[] args) {
        Pochi pochi = new Pochi();

        pochi.run();
    }
}
