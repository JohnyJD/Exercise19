package sk.brainit.nfqes.pki.api.loggers;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * File Logger - conditional
 * To enable file logger - set enableFileLogger to true and set path to log file
 *      boolean enableFileLogger = true/false
 *      String fileLogPath = path/to/log.txt
 */
public class FileLogger implements ILogger<String> {
    private final Logger logger = Logger.getLogger(FileLogger.class.getName());
    private final String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Logs String data from Evaluator to specified file
     * @param object String data
     */
    @Override
    public void log(String object) {
        try {
            File logFile = new File(this.filePath);
            try (FileOutputStream fos = new FileOutputStream(logFile)) {
                fos.write(object.getBytes());
            }
            logger.info("Logged to " + this.filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO exception", e);
        }

    }
}
