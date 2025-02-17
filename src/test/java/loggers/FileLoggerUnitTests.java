package loggers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.brainit.nfqes.pki.api.conditions.NumericCondition;
import sk.brainit.nfqes.pki.api.evaluators.NumericEvaluator;
import sk.brainit.nfqes.pki.api.loggers.FileLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

class FileLoggerUnitTests {
    @Test
    void fileLoggingTest() throws IOException {
        File f = File.createTempFile("log", "txt");

        FileLogger logger = new FileLogger(f.getAbsolutePath());
        NumericEvaluator ev = new NumericEvaluator();

        ev.addLogger(logger);
        ev.addConditions(List.of(new NumericCondition("DIV 2", "lol")));
        ev.setStart(1);
        ev.setEnd(4);
        ev.run();

        try (FileInputStream fis = new FileInputStream(f)) {
            String loggedData = new String(fis.readAllBytes());
            Assertions.assertEquals("1\nlol\n3\nlol\n", loggedData);
        }
    }
}
