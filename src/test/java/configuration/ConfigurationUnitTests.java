package configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.brainit.nfqes.pki.api.configuration.Config;
import sk.brainit.nfqes.pki.api.configuration.ConfigurationLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;

class ConfigurationUnitTests {
    String value = "{\"start\":1,\"end\":100,\"step\":1,\"expressions\":[{\"expression\":\"DIV 2\",\"result\":\"foo\"},{\"expression\":\"DIV 4\",\"result\":\"fuu\"}],\"enableFileLogger\":true,\"fileLogPath\":\"/log.txt\"}";

    @Test
    void deserializeConfiguration() throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(value.getBytes());
        Config config = ConfigurationLoader.deserialize(stream);

        Assertions.assertNotNull(config);
        Assertions.assertEquals(1,config.getStart());
        Assertions.assertEquals(100,config.getEnd());
        Assertions.assertEquals(1,config.getStep());
        Assertions.assertEquals(2,config.getExpressions().size());
        Assertions.assertTrue(config.isEnableFileLogger());
        Assertions.assertEquals("/log.txt",config.getFileLogPath());
    }
}
