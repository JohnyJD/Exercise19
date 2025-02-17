package conditions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sk.brainit.nfqes.pki.api.conditions.NumericCondition;
import sk.brainit.nfqes.pki.api.interpreters.IInterpreter;

class NumericConditionUnitTests {
    final String expression = "DIV 2";
    final String result = "foo";
    @Mock
    IInterpreter interpreter = Mockito.mock(IInterpreter.class);
    NumericCondition condition = new NumericCondition(expression, result);

    @Test
    void conditionNotMet() {
        Mockito.when(interpreter.interpret(Mockito.anyString(), Mockito.anyInt())).thenReturn(false);
        String result = condition.evaluate(interpreter, 2);
        Assertions.assertNull(result);
    }

    @Test
    void conditionMet() {
        Mockito.when(interpreter.interpret(Mockito.anyString(), Mockito.anyInt())).thenReturn(true);
        String res = condition.evaluate(interpreter, 2);
        Assertions.assertEquals(res, result);
    }
}
