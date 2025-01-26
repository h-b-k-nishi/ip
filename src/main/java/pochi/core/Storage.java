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
    private String DIR = "./data/", FILE = DIR + "log.txt";

    /**
     * Loads a log from previous session.
     * 
     * @return A list of strings representing log information.
     * @throws IOException Thrown when some error occurs during the file I/O.
     */
    public List<String> readLog() throws IOException  {
        List<String> res = new ArrayList<>();
        File folder = new File(DIR);
        if (!folder.exists()) {
            return res;
        }
        File logFile = new File(FILE);
        if (!logFile.exists()) {
            return res;
        }
        Scanner sc = new Scanner(logFile);
        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                res.add(line);
            }
        } finally {
            sc.close();
        }
        return res;
    }

    /**
     * Creates a file logging the information of tasks.
     * 
     * @param log The log that is going to be logged.
     * @throws IOException Thrown when an error is occurred during the file I/O.
     */
    public void createLog(String log) throws IOException {
        File folder = new File(DIR);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File logFile = new File(FILE);
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
