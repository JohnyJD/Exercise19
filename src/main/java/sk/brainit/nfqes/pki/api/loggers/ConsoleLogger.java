package sk.brainit.nfqes.pki.api.loggers;

/**
 * Basic console logger - default
 */
public class ConsoleLogger implements ILogger<String> {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
