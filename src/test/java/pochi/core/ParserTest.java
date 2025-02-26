package pochi.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import pochi.exceptions.CommandException;

/**
 * A class that tests Parser.
 *
 * @author Hibiki Nishiwaki
 */
public class ParserTest {
    /**
     * Performs a test on a case where the input string represents an event,
     * but it misses the /from argument.
     */
    @Test
    public void noFromTest() {
        try {
            Parser.parseCommand("event dinner 2025-02-05 19:00 /to 2025-02-05 20:00");

            assertEquals(0, 1);
        } catch (CommandException e) {
            assertEquals(e.getMessage(),
                    "Some arguments (/by, /from, /to, or the index of task) are missing!!");
        }
    }
    /**
     * Performs a test on a case where the input contains a lot of spaces.
     */
    @Test
    public void multipleSpacesTest() {
        try {
            List<String> parsed =
                Parser.parseCommand("todo  return  book   to     prof            taro");

            assertEquals(parsed.size(), 3);
            assertEquals(parsed.get(1), "return  book   to     prof            taro");
            assertEquals(parsed.get(2), "false");
        } catch (CommandException e) {
            assertEquals(0, 1);
        }
    }
}
