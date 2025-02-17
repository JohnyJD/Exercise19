package sk.brainit.nfqes.pki.api.interpreters.expressions;

/**
 * Represents logical operation || (OR)
 */
public class OrExpression implements IExpression {
    public IExpression leftSide;
    public IExpression rightSide;

    public OrExpression(IExpression leftSide) {
        this.leftSide = leftSide;
    }

    public OrExpression(IExpression leftSide, IExpression rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public void setRightSide(IExpression rightSide) {
        this.rightSide = rightSide;
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
