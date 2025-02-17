package sk.brainit.nfqes.pki.api.interpreters.expressions;

/**
 * Represents equals negation operation (not equals)
 */
public class NotEqualsExpression implements IExpression {
    private final int input;
    private final int value;

    public NotEqualsExpression(int input, int value) {
        this.input = input;
        this.value = value;
    }

    @Override
    public boolean interpret() {
        return input != value;
    }

    @Override
    public String toString() {
        return "NEQ" + value;
    }
}
