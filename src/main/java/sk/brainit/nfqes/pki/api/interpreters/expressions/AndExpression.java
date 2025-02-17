package sk.brainit.nfqes.pki.api.interpreters.expressions;

public class AndExpression implements IExpression {

    public IExpression leftSide;
    public IExpression rightSide;

    public AndExpression(IExpression leftSide) {
        this.leftSide = leftSide;
    }

    public void setRightSide(IExpression rightSide) {
        this.rightSide = rightSide;
    }

    public AndExpression(IExpression leftSide, IExpression rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
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
