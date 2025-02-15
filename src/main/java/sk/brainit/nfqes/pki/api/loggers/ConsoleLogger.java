package sk.brainit.nfqes.pki.api.loggers;

public class ConsoleLogger implements ILogger<String> {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
