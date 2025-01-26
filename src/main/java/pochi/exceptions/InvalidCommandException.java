package pochi.exceptions;
/**
 * An exception indicates that the command given by the user is invalid.
 * 
 * @author Hibiki Nishiwaki
 */
public class InvalidCommandException extends TaskCreationException {
    /**
     * Constructs a new instance of this exception.
     */
    public InvalidCommandException() {
        super("Please enter a valid one starting with: list / mark / unmark / todo / deadline / event.");
    }
}