package sk.brainit.nfqes.pki.api.interpreters;

import sk.brainit.nfqes.pki.api.interpreters.expressions.IExpression;

public interface IExpressionTreeBuilder {
    IExpression build(String expression, Integer input);
}
