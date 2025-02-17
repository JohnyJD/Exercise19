package evaluators;

import configuration.ConfigurationUnitTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.brainit.nfqes.pki.api.conditions.NumericCondition;
import sk.brainit.nfqes.pki.api.evaluators.NumericEvaluator;
import sk.brainit.nfqes.pki.api.loggers.FileLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

class EvaluatorUnitTests {
    NumericEvaluator evaluator = new NumericEvaluator();

    @Test
    void evaluationTest() {
        evaluator.addConditions(List.of(new NumericCondition("DIV 2", "foo")));
        String result = evaluator.evaluate(4);
        Assertions.assertEquals("foo\n", result);
    }

    @Test
    void loadConfigTest() throws IOException {
        File f = File.createTempFile("config", "json");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(ConfigurationUnitTests.configValue.getBytes());
        evaluator.load(f.getAbsolutePath());

        Assertions.assertEquals(1, evaluator.getStart());
        Assertions.assertEquals(100,evaluator.getEnd());
        Assertions.assertEquals(1, evaluator.getStep());
        Assertions.assertEquals(2, evaluator.getLoggers().size());
        Assertions.assertEquals(2, evaluator.getConditions().size());
    }
}
