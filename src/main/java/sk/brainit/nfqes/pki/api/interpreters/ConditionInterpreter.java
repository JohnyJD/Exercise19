package sk.brainit.nfqes.pki.api.interpreters;

import sk.brainit.nfqes.pki.api.interpreters.expressions.IExpression;

public class ConditionInterpreter implements IInterpreter {
    private final IExpressionTreeBuilder treeBuilder;

    public ConditionInterpreter() {
        this.treeBuilder = ExpressionTreeBuilder.getInstance();
    }

    @Override
    public boolean interpret(String expression, Integer input) {
        return buildExpressionTree(expression, input)
                .interpret();
    }

    private IExpression buildExpressionTree(String expression, Integer input) {
        return treeBuilder.build(expression, input);
    }


}
