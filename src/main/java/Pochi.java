import java.util.Scanner;

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
        if (command.equals("list")) {
            storage.listUp();
        } else {
            storage.addTask(new Task(command));
        }
        System.out.println();
    }

    private void run() {
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        while (true){
            String command = sc.nextLine();
            if (command.equals("bye")) break;
            processCommand(command);
        }
        System.out.println(farewell);
    }
    public static void main(String[] args) {
        Pochi pochi = new Pochi();
        pochi.run();
    }
}
