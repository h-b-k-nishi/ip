import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class represents a stroage, which contains all the information of task.
 * 
 * @author Hibiki Nishiwaki
 */
public class Storage {
    private final List<Task> tasks;
    /**
     * The constructor of storage, which creates an empty array of tasks.
     */
    public Storage() {
        tasks = new ArrayList<>();
    }

    /**
     * List up the tasks stored.
     */
    public void listUp() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    /**
     * Mark a task as completed.
     * 
     * @param index The index of task marked. 
     * It has to satisfy: 1 <= index <= (current number of tasks).
     */
    public void mark(int index) throws IndexOutOfBoundException {
        if (1 <= index && index <= tasks.size()) {
            tasks.get(index-1).mark();
            System.out.println("Wonderful! I've marked this task as completed:\n" + tasks.get(index-1));
        } else {
            throw new IndexOutOfBoundException(tasks.size());
        }
    }

    /**
     * Mark a task as incompleted.
     * 
     * @param index The index of task unmarked. 
     * It has to satisfy: 1 <= index <= (current number of tasks).
     */
    public void unmark(int index) throws IndexOutOfBoundException {
        if (1 <= index && index <= tasks.size()) {
            tasks.get(index-1).unmark();
            System.out.println("Okay, I've marked this task as incompleted:\n" + tasks.get(index-1));
        } else {
            throw new IndexOutOfBoundException(tasks.size());
        }
    }

    /**
     * Remove a task.
     * 
     * @param index The index of task removed.
     */
    public void delete(int index) throws IndexOutOfBoundException {
        if (1 <= index && index <= tasks.size()) {
            System.out.println("Noted. I've removed this task:\n" + tasks.get(index-1));
            tasks.remove(index-1);
        } else {
            throw new IndexOutOfBoundException(tasks.size());
        }
    }

    /**
     * Add a new task.
     * 
     * @param task The new task going to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }

    /**
     * Print out the number of tasks in the list.
     */
    public void printStatus() {
        System.out.println("Now you have " + tasks.size()+ " tasks in the list.");
    }

    /**
     * Creates a file logging the information of tasks.
     */
    public void createLog() throws IOException {
        String DIR = "./../data/", FILE = "log.txt";
        File folder = new File(DIR);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File logFile = new File(DIR + FILE);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        FileWriter fw = new FileWriter(logFile);
        try {
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).log() + "\n");
            }
        } finally {
            fw.close();
        }
    }
}
