import java.util.Scanner;
import java.util.List;

/**
 * A class represents a chatbot Pochi.
 * 
 * @author Hibiki Nishiwaki
 */
public class Pochi {
    private static final String greet = "Hello! I'm Pochi.\n"
        + "What can I do for you?\n",
        farewell = "Bye. Hope to see you again soon!\n";
    
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
        } else {
            try {
                storage.addTask(Task.of(info));
            } catch (CommandException e) {
                throw e;
            }
        }
    }

    private void run() {
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        while (true){
            String command = sc.nextLine();
            if (command.isEmpty()) {
                continue;
            }
            if (command.equals("bye")) {
                System.out.println(farewell);
                break;
            }
            try {
                processCommand(command);
                storage.printStatus();
            } catch (EmptyCommandException e) {
                // Do noting
            } catch (CommandException e) {
                System.out.println("Oops! Some error occurred!");
                System.out.println(e);
            } finally {
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
