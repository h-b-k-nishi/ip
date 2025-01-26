import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class deals with loading tasks from the file and saving tasks in the file.
 * 
 * @author Hibiki Nishiwaki
 */
public class Storage {
    private String DIR = "./../data/", FILE = DIR + "log.txt";

    /**
     * Creates a file logging the information of tasks.
     * 
     * @param log The log that is going to be logged.
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
