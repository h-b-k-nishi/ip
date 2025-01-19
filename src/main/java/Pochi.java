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

    private void run() {
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        while (true){
            String command = sc.nextLine();
            if (command.equals("bye")) break;
            System.out.println(command);
        }
        System.out.println(farewell);
    }
    public static void main(String[] args) {
        Pochi pochi = new Pochi();
        pochi.run();
    }
}
