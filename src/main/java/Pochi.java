import java.util.Scanner;
import java.io.IOException;
import java.util.List;

/**
 * A class represents a chatbot Pochi.
 * 
 * @author Hibiki Nishiwaki
 */
public class Pochi {
    private static final String GREET = "Hello! I'm Pochi.\n"
        + "What can I do for you?\n",
        FAREWELL = "Bye. Hope to see you again soon!\n";
    
    private final Storage storage;

    private Pochi() {
        storage = new Storage();
    }

    private void processCommand(String command) throws CommandException {
        List<String> info = List.of(command.split(" "));
        if (info.isEmpty()) {
            throw new EmptyCommandException();
        }
        if (info.get(0).equals("list")) {
            storage.listUp();
        } else if(info.get(0).equals("mark")) {
            if (info.size() < 2) {
                throw new MissingArgumentException();
            }
            int index = Integer.parseInt(info.get(1));
            try {
                storage.mark(index);
            } catch (IndexOutOfBoundsException e) {
                throw e;
            }
        } else if (info.get(0).equals("unmark")) {
            if (info.size() < 2) {
                throw new MissingArgumentException();
            }
            int index = Integer.parseInt(info.get(1));
            try {
                storage.unmark(index);
            } catch (IndexOutOfBoundsException e) {
                throw e;
            }
        } else if (info.get(0).equals("delete")) {
            if (info.size() < 2) {
                throw new MissingArgumentException();
            }
            int index = Integer.parseInt(info.get(1));
            try {
                storage.delete(index);
            } catch (IndexOutOfBoundsException e) {
                throw e;
            }
        } else {
            try {
                storage.addTask(Task.of(info));
            } catch (CommandException e) {
                throw e;
            }
        }
    }

    private void run() {
        System.out.println(GREET);
        Scanner sc = new Scanner(System.in);
        while (true){
            String command = sc.nextLine();
            if (command.isEmpty()) {
                continue;
            }
            if (command.equals("bye")) {
                System.out.println(FAREWELL);
                break;
            }
            try {
                processCommand(command);
                storage.printStatus();
                storage.createLog();
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
        sc.close();
    }
    public static void main(String[] args) {
        Pochi pochi = new Pochi();
        pochi.run();
    }
}
