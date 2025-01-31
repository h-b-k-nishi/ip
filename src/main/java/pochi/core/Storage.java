package pochi.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Hibiki Nishiwaki
 */
public class Storage {
    /** A string representing the path to the directly, which contains the log file. */
    private static String DIRECTORY_PATH = "./data/";

    /** A string representing the path to the log file. */
    private static String FILE_PATH = DIRECTORY_PATH + "log.txt";

    /**
     * Loads a log from previous session.
     *
     * @return A list of strings representing log information.
     * @throws IOException Thrown when some error occurs during the file I/O.
     */
    public List<String> readLog() throws IOException {
        List<String> results = new ArrayList<>();

        File folder = new File(Storage.DIRECTORY_PATH);

        if (!folder.exists()) {
            return results;
        }

        File logFile = new File(Storage.FILE_PATH);

        if (!logFile.exists()) {
            return results;
        }

        Scanner scanner = new Scanner(logFile);

        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                results.add(line);
            }
        } finally {
            scanner.close();
        }

        return results;
    }

    /**
     * Creates a file logging the information of tasks.
     *
     * @param log The log that is going to be logged.
     * @throws IOException Thrown when an error is occurred during the file I/O.
     */
    public void createLog(String log) throws IOException {
        File folder = new File(Storage.DIRECTORY_PATH);

        if (!folder.exists()) {
            folder.mkdir();
        }

        File logFile = new File(Storage.FILE_PATH);

        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        FileWriter fw = new FileWriter(logFile);

        try {
            fw.write(log);
        } finally {
            fw.close();
        }
    }
}
