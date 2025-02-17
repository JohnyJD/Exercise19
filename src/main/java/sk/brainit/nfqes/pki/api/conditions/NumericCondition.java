package sk.brainit.nfqes.pki.api.conditions;

import sk.brainit.nfqes.pki.api.interpreters.IInterpreter;

/**
 * Condition representing numerical evaluations
 * Input:  Integer
 * Output: String
 * Evaluations are done through interpreter
 */
public class NumericCondition implements ICondition<Integer, String> {
    private final String expression;
    private final String resultValue;

    public NumericCondition(String expression, String resultValue) {
        this.expression = expression;
        this.resultValue = resultValue;
    }

    /**
     * Evaluate input data with interpreter
     * @param interpreter Interpreter abstraction
     * @param input Input data Integer
     * @return Output data String
     */
    @Override
    public String evaluate(IInterpreter interpreter, Integer input) {
        return interpreter.interpret(expression, input) ? resultValue : null;
    }
}
