package pochi.exceptions;

/**
 * An exception indicates that the description of task is empty
 * (i.e. missing) from the user input.
 * 
 * @author Hibiki Nishiwaki
 */
public class EmptyDescriptionException extends TaskCreationException {
    /**
     * The constructor of this exception.
     */
    public EmptyDescriptionException() {
        super("Your task description (i.e. task name) is empty!");
    }
}
