package sk.brainit.nfqes.pki.api.loggers;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLogger implements ILogger<String> {
    private final Logger logger = Logger.getLogger(FileLogger.class.getName());
    private final String filePath;
    public FileLogger(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public void log(String object) {
        try {
            File logFile = new File(this.filePath);

            try(FileWriter writer = new FileWriter(logFile, true);BufferedWriter br = new BufferedWriter(writer)) {
                br.write(object + "\r\n");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO exception", e);
        }

    }
}
