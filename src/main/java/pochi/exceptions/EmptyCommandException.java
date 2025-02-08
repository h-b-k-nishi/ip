package pochi.exceptions;

/**
 * An exception indicates that a command given by the user is empty.
 *
 * @author Hibiki Nishiwaki
 */
public class EmptyCommandException extends CommandException {
    /**
     * Constructs a new instance of this exception.
     */
    public EmptyCommandException() {
        super("Your command is empty. Please enter something!!\n");
    }
}
