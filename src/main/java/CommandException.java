/**
 * An abstract class that encapsulates all the exceptions 
 * occurred during the procession of a command.
 * 
 * @author Hibiki Nishiwaki
 */
public abstract class CommandException extends Exception {
    /**
     * The constructor of exception without description.
     */
    public CommandException() {
        super();
    }
    /**
     * The constructor of exception.
     * 
     * @param description A string describing this exceptional situation.
     */
    public CommandException(String description) {
        super(description);
    }
}
