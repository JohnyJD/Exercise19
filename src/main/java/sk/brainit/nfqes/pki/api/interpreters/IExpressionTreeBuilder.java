package sk.brainit.nfqes.pki.api.interpreters;

import sk.brainit.nfqes.pki.api.interpreters.expressions.IExpression;

/**
 * Expression tree builder abstraction
 * Marks class used for expression building
 */
public interface IExpressionTreeBuilder {
    IExpression build(String expression, Integer input);
}
