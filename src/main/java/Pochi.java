import java.util.Scanner;

/**
 * A class represents a chatbot Pochi.
 * 
 * @author Hibiki Nishiwaki
 */
public class Pochi {
    public static void main(String[] args) {
        String greet = "Hello! I'm Pochi.\n"
                + "What can I do for you?\n",
                farewell = "Bye. Hope to see you again soon!\n";
        System.out.println(greet);
        
        Scanner sc = new Scanner(System.in);
        while (true){
            String command = sc.nextLine();
            if (command.equals("bye")) break;
            System.out.println(command);
        }
        System.out.println(farewell);
    }
}
