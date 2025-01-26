package pochi.core;
import java.io.IOException;
import java.util.List;
import pochi.exceptions.*;
import pochi.tasks.Task;

/**
 * A class represents a chatbot Pochi.
 * 
 * @author Hibiki Nishiwaki
 */
public class Pochi {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    private Pochi() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    private void processCommand(List<String> command) throws CommandException {
        if (command.get(0).equals("list")) {
            ui.printList(tasks.status());
        } else if(command.get(0).equals("mark")) {
            int index = Integer.parseInt(command.get(1));
            Task marked = tasks.mark(index);
            ui.markTask(marked.toString());
        } else if (command.get(0).equals("unmark")) {
            int index = Integer.parseInt(command.get(1));
            Task unmarked = tasks.unmark(index);
            ui.unmarkTask(unmarked.toString());
        } else if (command.get(0).equals("delete")) {
            int index = Integer.parseInt(command.get(1));
            Task removed = tasks.delete(index);
            ui.removeTask(removed.toString());
        } else {
            Task added = tasks.addTask(Task.of(command));
            ui.addTask(added.toString());
        }
    }

    private void processPreviousLog() {
        try {
            List<String> logs = storage.readLog();
            for (int i = 0; i < logs.size(); i++) {
                tasks.addTask(Task.of(List.of(logs.get(i).split(" \\| "))));
            }
            if (!tasks.isEmpty()) {
                ui.completeLoad();
                ui.printList(tasks.status());
                ui.changeLine();
            }
        } catch (Exception e) {
            ui.printError("Oops! Some error occurred when loading the log from the previous session.");
            ui.printError("The history of previous session is lost. I am very sorry...");
            ui.changeLine();
        }
    }

    private void run() {
        processPreviousLog();
        while (true){
            String command = ui.readInput();
            if (command.isEmpty()) {
                continue;
            }
            try {
                List<String> parsed = Parser.parse(command);
                if (parsed.get(0).equals("bye")) {
                    ui.exit();
                    break;
                }
                processCommand(parsed);
                ui.printStatus(tasks.getNumberOfTasks());
                storage.createLog(tasks.log());
            } catch (EmptyCommandException e) {
                // Do noting
            } catch (CommandException e) {
                ui.printError("Oops! Some error occurred!");
                ui.printError(e.toString());
            } catch (IOException e) {
                ui.printError("Oops! Some error occurred during the creation of log file.");
                ui.printError("Please note that the current status of tasks is not saved, sorry...");
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
