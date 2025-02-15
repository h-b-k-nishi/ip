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


    private void createFolder(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            file.mkdir();
        }
    }

    private File createFile(String fileName) throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    private List<String> scanFile(File file) throws IOException {
        Scanner scanner = new Scanner(file);

        List<String> results = new ArrayList<>();

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

    private void writeLog(File file, String log) throws IOException {
        FileWriter fw = new FileWriter(file);
        try {
            fw.write(log);
        } finally {
            fw.close();
        }
    }

    /**
     * Loads a log from previous session.
     *
     * @return A list of strings representing log information.
     * @throws IOException Thrown when some error occurs during the file I/O.
     */
    public List<String> readLog() throws IOException {
        createFolder(Storage.DIRECTORY_PATH);

        File logFile = createFile(Storage.FILE_PATH);

        return scanFile(logFile);
    }

    /**
     * Creates a file logging the information of tasks.
     *
     * @param log The log that is going to be logged.
     * @throws IOException Thrown when an error is occurred during the file I/O.
     */
    public void createLog(String log) throws IOException {
        createFolder(Storage.DIRECTORY_PATH);

        File logFile = createFile(Storage.FILE_PATH);

        writeLog(logFile, log);
    }
}
