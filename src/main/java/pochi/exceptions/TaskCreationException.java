package pochi.exceptions;

/**
 * An abstract class that encapsulates all the exceptions 
 * occurred during a creation of task.
 * 
 * @author Hibiki Nishiwaki
 */
public abstract class TaskCreationException extends CommandException {
    /**
     * Constructs a new instance of this exception.
     * 
     * @param description A string describing this exceptional situation.
     */
    public TaskCreationException(String description) {
        super(description);
    }
}
