import java.util.ArrayList;
import java.util.List;

/**
 * A class manages the list of tasks.
 * 
 * @author Hibiki Nishiwaki
 */
public class TaskList {
    private final List<Task> tasks;
    /**
     * The constructor of task list, which creates an empty array of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
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
     * Returns the number of tasks.
     * 
     * @return The current length of task list.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Returns a list of strings, representing tasks in the list.
     * 
     * @return The list of string representation.
     */
    public List<String> status() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            res.add((i+1) + ". " + tasks.get(i));
        }
        return res;
    }

    /**
     * Returns a string consisting of the log representations of tasks.
     * 
     * @return The string represetation of log.
     */
    public String log() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += tasks.get(i).log() + "\n";
        }
        return res;
    }
}
