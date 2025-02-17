package sk.brainit.nfqes.pki.api.interpreters.expressions;

/**
 * Represents equals operation
 */
public class EqualsExpression implements IExpression {
    private final int input;
    private final int value;

    public EqualsExpression(int input, int value) {
        this.input = input;
        this.value = value;
    }

    @Override
    public boolean interpret() {
        return input == value;
    }

    @Override
    public String toString() {
        return "EQ " + value;
    }
}
