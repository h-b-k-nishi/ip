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
     * Add a new task.
     * 
     * @param task The new task going to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }
}
