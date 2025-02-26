package pochi.core;

import java.util.ArrayList;
import java.util.List;

import pochi.exceptions.IndexOutOfBoundException;
import pochi.tasks.Task;

/**
 * A class manages the list of tasks.
 *
 * @author Hibiki Nishiwaki
 */
public class TaskList {
    /** A list of tasks. */
    private final List<Task> tasks;

    /**
     * Constructs a new TaskList, initializing an empty array of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new task.
     *
     * @param task The new task going to be added.
     * @return The newly added task.
     */
    public Task addTask(Task task) {
        tasks.add(task);

        return task;
    }

    /**
     * Removes a task.
     *
     * @param index The index of task removed. It has to satisfy: 1 <= index <= (current number of tasks).
     * @return The removed task.
     * @throws IndexOutOfBoundException Thrown when the given index is out of range.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundException {
        if (1 <= index && index <= tasks.size()) {
            Task removed = tasks.get(index - 1);
            tasks.remove(index - 1);
            return removed;
        } else {
            throw new IndexOutOfBoundException(tasks.size());
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of task marked. It has to satisfy: 1 <= index <= (current number of tasks).
     * @return The marked task.
     * @throws IndexOutOfBoundException Thrown when the given index is out of range.
     */
    public Task markTask(int index) throws IndexOutOfBoundException {
        if (1 <= index && index <= tasks.size()) {
            Task marked = tasks.get(index - 1);
            marked.mark();
            return marked;
        } else {
            throw new IndexOutOfBoundException(tasks.size());
        }
    }

    /**
     * Marks a task as incompleted.
     *
     * @param index The index of task unmarked. It has to satisfy: 1 <= index <= (current number of tasks).
     * @return The unmarked task.
     * @throws IndexOutOfBoundException Thrown when the given index is out of range.
     */
    public Task unmarkTask(int index) throws IndexOutOfBoundException {
        if (1 <= index && index <= tasks.size()) {
            Task unmarked = tasks.get(index - 1);
            unmarked.unmark();
            return unmarked;
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
        return tasks.size();
    }

    /**
     * Checks if this task list is empty.
     *
     * @return True if it is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a list of strings, representing tasks in the list.
     *
     * @return The string representation of tasks.
     */
    public String getStatus() {
        String description = "";

        for (int i = 0; i < tasks.size(); i++) {
            description += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }

        return description;
    }

    /**
     * Returns a string consisting of the log representations of tasks.
     *
     * @return The string represetation of log.
     */
    public String getLog() {
        String log = "";

        for (int i = 0; i < tasks.size(); i++) {
            log += tasks.get(i).getLog() + "\n";
        }

        return log;
    }

    /**
     * Returns a list of strings, representing tasks that contain the specified keyword.
     *
     * @param keyword The specified keyword.
     * @return The list of string representations.
     */
    public List<String> findTask(String keyword) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).containsKeyword(keyword)) {
                res.add((i + 1) + ". " + tasks.get(i));
            }
        }
        return res;
    }
}
