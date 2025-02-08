package pochi.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that deals with the settings of CUI.
 *
 * @author Hibiki Nishiwaki
 */
public abstract class Ui {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm");

    /**
     * Returns a desirable format of date.
     *
     * @param dateAndTime A LocalDateTime converted to the desirable format.
     * @return A proper string representation of date.
     */
    public static String formatDateTime(LocalDateTime dateAndTime) {
        return dateAndTime.format(dateTimeFormatter);
    }
}
