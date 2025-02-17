package interpreters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.brainit.nfqes.pki.api.interpreters.ConditionInterpreter;
import sk.brainit.nfqes.pki.api.interpreters.IInterpreter;

class InterpreterUnitTests {
    IInterpreter interpreter = new ConditionInterpreter();
    @Test
    void invalidExpression() {
        Exception ex = Assertions.assertThrows(
                Exception.class,
                () -> interpreter.interpret("BAD ExPression", 10)
        );
        Assertions.assertEquals("Invalid Expression", ex.getMessage());
    }

    @Test
    void validAndExpression() {
        boolean result = interpreter.interpret(
                "DIV 2 AND DIV 5",
                10
        );
        Assertions.assertTrue(result);
    }

    @Test
    void validOrExpression() {
        boolean result = interpreter.interpret(
                "NEQ 2 OR EQ 10",
                10
        );
        Assertions.assertTrue(result);
    }
}
