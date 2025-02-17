package sk.brainit.nfqes.pki.api.interpreters.expressions.logicals;

import lombok.Setter;
import sk.brainit.nfqes.pki.api.interpreters.expressions.IExpression;

/**
 * Represents logical operation || (OR)
 */
public class OrExpression implements IExpression {
    public IExpression leftSide;
    @Setter
    public IExpression rightSide;

    public OrExpression(IExpression leftSide) {
        this.leftSide = leftSide;
    }

    @Override
    public boolean interpret() {
        return leftSide.interpret() || rightSide.interpret();
    }

    @Override
    public String toString() {
        return leftSide.toString() + " OR " + rightSide.toString();
    }
}
