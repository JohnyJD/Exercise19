package sk.brainit.nfqes.pki.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Serves to load configuration data from configuration file (.json)
 * specified with path
 */
public class ConfigurationLoader {
    private static final Logger logger = Logger.getLogger(ConfigurationLoader.class.getName());

    private ConfigurationLoader() {}

    /**
     * Loads configuration data from file (.json)
     * @param path Config path
     * @return Config
     */
    public static Config loadConfig(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                Config config = deserialize(fileInputStream);
                logger.info("Configuration loaded");
                return config;
            }
            return null;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error while reading configuration", e);
            return null;
        }
    }

    /**
     * Deserialize Input stream to Config object
     * @param inputStream Config Input stream
     * @return Config
     * @throws IOException Exception during deserialization
     */
    public static Config deserialize(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, Config.class);
    }
}
