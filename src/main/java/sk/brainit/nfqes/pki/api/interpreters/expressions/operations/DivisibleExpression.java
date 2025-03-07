package sk.brainit.nfqes.pki.api.interpreters.expressions.operations;


import sk.brainit.nfqes.pki.api.interpreters.expressions.IExpression;

/**
 * Represents divisibility - input is divisible by divisor
 */
public class DivisibleExpression implements IExpression {
    private final int input;
    private final int divisor;

    public DivisibleExpression(int input, int divisor) {
        this.input = input;
        this.divisor = divisor;
    }


    @Override
    public boolean interpret() {
        return input % divisor == 0;
    }

    @Override
    public String toString() {
        return "DIV " + divisor;
    }
}
