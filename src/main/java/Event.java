/**
 * A class that represents a Event task.
 * 
 * @author Hibiki Nishiwaki
 */
public class Event extends Task {
    private final String startTime;
    private final String endTime;
    /**
     * The constructor of Event task.
     * 
     * @param description The description of task.
     * @param startTime The start time of event.
     * @param endTime The end time of event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Return the string representation of this Event task.
     * 
     * @return The string consisting of the description of task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() 
        + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
