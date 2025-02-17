package sk.brainit.nfqes.pki.api.interpreters.expressions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Special expression
 * Cannot be defined (yet)
 * Creates groups of operations
 * Mainly used as root element
 */
@NoArgsConstructor
@Setter
public class GroupExpression implements IExpression {
    private IExpression expression;
    @Setter
    @Getter
    private IExpression parent;

    @Override
    public boolean interpret() {
        return expression.interpret();
    }

    @Override
    public String toString() {
        return "|" + this.expression.toString() + "|";
    }
}
