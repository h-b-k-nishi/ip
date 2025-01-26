package pochi.exceptions;
/**
 * An abstract class that encapsulates all the exceptions 
 * occurred during the procession of a command.
 * 
 * @author Hibiki Nishiwaki
 */
public abstract class CommandException extends Exception {
    /**
     * Constructs a new instance of this exception.
     */
    public CommandException() {
        super();
    }

    /**
     * Constructs a new instance of this exception.
     * 
     * @param description A string describing this exceptional situation.
     */
    public CommandException(String description) {
        super(description);
    }
}
