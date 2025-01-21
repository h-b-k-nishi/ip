/**
 * An abstract class that encapsulates all the exceptions 
 * occurred during a creation of task.
 * 
 * @author Hibiki Nishiwaki
 */
public abstract class TaskCreationException extends CommandException {
    /**
     * The constructor of exception.
     * 
     * @param description A string describing this exceptional situation.
     */
    public TaskCreationException(String description) {
        super(description);
    }
}
