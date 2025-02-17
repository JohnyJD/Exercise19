package sk.brainit.nfqes.pki.api.interpreters.expressions.operations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.brainit.nfqes.pki.api.interpreters.expressions.IExpression;

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

    @Override
    public boolean interpret() {
        return expression.interpret();
    }

    @Override
    public String toString() {
        return "|" + this.expression.toString() + "|";
    }
}
