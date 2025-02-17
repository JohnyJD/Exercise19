package app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sk.brainit.nfqes.pki.api.app.EvaluationApp;
import sk.brainit.nfqes.pki.api.evaluators.NumericEvaluator;

public class EvaluationAppTests {
    NumericEvaluator evaluator = Mockito.mock(NumericEvaluator.class);
    @Test
    void appStartUp_badConfigNameTest() {
        EvaluationApp app = EvaluationApp.getInstance();
        app.load("ab§§dksôaô.dldld");
        Assertions.assertDoesNotThrow(app::start);
    }
}
