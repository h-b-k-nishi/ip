/**
 * An exception indicates that a date given by the user has an invalid format.
 * 
 * @author Hibiki Nishiwaki
 */
public class InvalidDateException extends TaskCreationException {
    /**
     * The constructor of this exception.
     */
    public InvalidDateException() {
        super("Invalid format of date! The format has to be yyyy-mm-dd hh:mm.");
    }
}
