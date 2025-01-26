package pochi.tasks;
import java.time.LocalDateTime;
import pochi.core.Ui;
import pochi.exceptions.EmptyDescriptionException;
/**
 * A class that represents a Event task.
 * 
 * @author Hibiki Nishiwaki
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    /**
     * The constructor of Event task.
     * 
     * @param description The description of task.
     * @param startTime The start time of event.
     * @param endTime The end time of event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) throws EmptyDescriptionException {
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
        return "[E] " + super.toString() + " (from: " 
        + Ui.formatDateTime(this.startTime) + " to: " + Ui.formatDateTime(this.endTime) + ")";
    }

    /**
     * Returns a short description of this task.
     * 
     * @return The string description
     */
    @Override
    public String log() {
        return "event | " + super.log() + " | " + this.startTime + " | " + this.endTime;
    }
}
