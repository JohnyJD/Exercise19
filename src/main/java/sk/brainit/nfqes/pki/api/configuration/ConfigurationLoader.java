package sk.brainit.nfqes.pki.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationLoader {
    private static final Logger logger = Logger.getLogger(ConfigurationLoader.class.getName());

    private ConfigurationLoader() {}

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

    public static Config deserialize(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, Config.class);
    }
}
