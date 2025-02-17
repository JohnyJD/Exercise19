package sk.brainit.nfqes.pki.api.loggers;

/**
 * Logger abstraction
 * @param <T> Object to be logged
 */
public interface ILogger<T> {
    void log(T object);
}
