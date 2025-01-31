package pochi.exceptions;

/**
 * An exception indicates that a operation against an element of strorage,
 * denoted by its index, is out of range.
 *
 * @author Hibiki Nishiwaki
 */
public class IndexOutOfBoundException extends CommandException {
    /**
     * Constructs a new instance of this exception.
     *
     * @param sizeOfStorage The current size of storage (i.e. the lengh of array tasks).
     */
    public IndexOutOfBoundException(int sizeOfStorage) {
        super("The given index is out of bound.\n"
                + "You have " + sizeOfStorage + " tasks currently, "
                        + "so input an index between 1 and " + sizeOfStorage + " inclusive.");
    }
}
