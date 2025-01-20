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

    private void processCommand(String command) {
        List<String> info = List.of(command.split(" "));
        if (info.get(0).equals("list")) {
            storage.listUp();
        } else if(info.get(0).equals("mark") && info.size() >= 2) {
            int index = Integer.parseInt(info.get(1));
            storage.mark(index);
        } else if (info.get(0).equals("unmark") && info.size() >= 2) {
            int index = Integer.parseInt(info.get(1));
            storage.unmark(index);
        } else {
            storage.addTask(new Task(command));
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
            processCommand(command);
            storage.printStatus();
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Pochi pochi = new Pochi();
        pochi.run();
    }
}
