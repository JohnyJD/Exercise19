package sk.brainit.nfqes.pki.api.configuration;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationLoader {
    private static final Logger logger = Logger.getLogger(ConfigurationLoader.class.getName());

    private ConfigurationLoader() {}

    public static Config loadConfig(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Config config = objectMapper.readValue(file, Config.class);
                logger.info("Configuration loaded");
                return config;
            }
            return null;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error while reading configuration", e);
            return null;
        }

    }
}
