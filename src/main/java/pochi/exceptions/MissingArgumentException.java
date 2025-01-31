package pochi.exceptions;

/**
 * An exception indicates that some necessary arguments are missing from the user input.
 *
 * @author Hibiki Nishiwaki
 */
public class MissingArgumentException extends TaskCreationException {
    /**
     * Constructs a new instance of this exception.
     */
    public MissingArgumentException() {
        super("Some arguments (/by, /from, /to, or the index of task) are missing!!");
    }
}
