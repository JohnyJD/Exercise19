package sk.brainit.nfqes.pki.api.conditions;

import sk.brainit.nfqes.pki.api.interpreters.IInterpreter;

public class NumericCondition implements ICondition<Integer, String> {
    private final String expression;
    private final String resultValue;

    public NumericCondition(String expression, String resultValue) {
        this.expression = expression;
        this.resultValue = resultValue;
    }

    @Override
    public String evaluate(IInterpreter interpreter, Integer input) {
        return interpreter.interpret(expression, input) ? resultValue : null;
    }
}
