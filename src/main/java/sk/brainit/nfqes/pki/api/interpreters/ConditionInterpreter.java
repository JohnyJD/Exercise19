package sk.brainit.nfqes.pki.api.interpreters;

import sk.brainit.nfqes.pki.api.interpreters.expressions.IExpression;

/**
 * Interpreter used for translating numerical operations from special syntax
 * to expressions which represents the same logic
 * More flexibility in code
 * Less configuration overhead
 */
public class ConditionInterpreter implements IInterpreter {
    private final IExpressionTreeBuilder treeBuilder;

    public ConditionInterpreter() {
        this.treeBuilder = ExpressionTreeBuilder.getInstance();
    }

    /**
     * Interprets expression chain to logical operations
     * @param expression Expression root element
     * @param input Input value for logical operations
     * @return boolean Is input valid according to logic ?
     */
    @Override
    public boolean interpret(String expression, Integer input) {
        return buildExpressionTree(expression, input)
                .interpret();
    }

    /**
     * Builds expression tree which is representing expression structure
     * @param expression Expression in simple syntax
     * @param input Input value for logical operations
     * @return Expression root element
     */
    private IExpression buildExpressionTree(String expression, Integer input) {
        return treeBuilder.build(expression, input);
    }


}
