package sk.brainit.nfqes.pki.api.interpreters.expressions;

import lombok.Setter;

/**
 * Represents logical operation && (AND)
 */
public class AndExpression implements IExpression {

    public IExpression leftSide;
    @Setter
    public IExpression rightSide;

    public AndExpression(IExpression leftSide) {
        this.leftSide = leftSide;
    }

    @Override
    public boolean interpret() {
        return leftSide.interpret() && rightSide.interpret();
    }

    @Override
    public String toString() {
        return leftSide.toString() + " AND " + rightSide.toString();
    }
}
