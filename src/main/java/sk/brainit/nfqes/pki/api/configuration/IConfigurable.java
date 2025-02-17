package sk.brainit.nfqes.pki.api.configuration;

/**
 * Abstraction meant to mark class as configurable
 * Such a class might implement loading configuration data to instance
 */
public interface IConfigurable {
    void load(String path);
}
