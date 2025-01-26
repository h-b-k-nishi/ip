import java.io.IOException;
import java.util.List;

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
        if (command.isEmpty()) {
            throw new EmptyCommandException();
        }
        if (command.get(0).equals("list")) {
            ui.printList(tasks.status());
        } else if(command.get(0).equals("mark")) {
            if (command.size() < 2) {
                throw new MissingArgumentException();
            }
            int index = Integer.parseInt(command.get(1));
            tasks.mark(index);
        } else if (command.get(0).equals("unmark")) {
            if (command.size() < 2) {
                throw new MissingArgumentException();
            }
            int index = Integer.parseInt(command.get(1));
            tasks.unmark(index);
        } else if (command.get(0).equals("delete")) {
            if (command.size() < 2) {
                throw new MissingArgumentException();
            }
            int index = Integer.parseInt(command.get(1));
            tasks.delete(index);
        } else {
            tasks.addTask(Parser.parseTask(command));
        }
    }

    private void run() {
        while (true){
            List<String> command = ui.readInput();
            if (command.isEmpty()) {
                continue;
            }
            if (command.get(0).equals("bye")) {
                break;
            }
            try {
                processCommand(command);
                ui.printStatus(tasks.getNumberOfTasks());
                storage.createLog(tasks.log());
            } catch (EmptyCommandException e) {
                // Do noting
            } catch (CommandException e) {
                System.out.println("Oops! Some error occurred!");
                System.out.println(e);
            } catch (IOException e) {
                System.out.println("Oops! Some error occurred during the creating of log file.");
                System.out.println("Please note that the current status of tasks is not saved, sorry...");
            }finally {
                System.out.println();
            }
        }
        ui.exit();
    }
    public static void main(String[] args) {
        Pochi pochi = new Pochi();
        pochi.run();
    }
}
