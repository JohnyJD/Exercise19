package sk.brainit.nfqes.pki.api.interpreters;

import sk.brainit.nfqes.pki.api.interpreters.expressions.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpressionTreeBuilder implements IExpressionTreeBuilder {
    private Logger logger = Logger.getLogger(ExpressionTreeBuilder.class.getName());
    private AtomicInteger index;
    private static ExpressionTreeBuilder instance;
    private Integer input;

    private ExpressionTreeBuilder() {
        reset();
    }

    public static ExpressionTreeBuilder getInstance() {
        if (instance == null) {
            instance = new ExpressionTreeBuilder();
        }
        return instance;
    }

    @Override
    public IExpression build(String expression, Integer input) {
        this.input = input;
        expression = expression.trim();
        String[] parts = expression.split(" ");
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setExpression(
                getExpression(parts, groupExpression)
        );
        reset();
        return groupExpression;
    }

    private IExpression getExpression(String[] expressionParts, IExpression parent) {
        if (index.get() >= expressionParts.length) {
            return parent;
        }

        IExpression expression = checkForExpression(expressionParts);
        if(expression != null) {
            increment();
            if (index.get() >= expressionParts.length) {
                return expression;
            }
            IExpression logicalExpression = checkForLogicalExpression(expressionParts, expression);
            if(logicalExpression != null) {
                return logicalExpression;
            }
            return expression;
        }

        IExpression logicalExpression = checkForLogicalExpression(expressionParts, parent);
        if(logicalExpression != null) {
            return logicalExpression;
        }
        throw new RuntimeException("Invalid Expression");
    }

    private IExpression checkForExpression(String[] expressionParts) {
        String part = expressionParts[index.get()];
        if(part.equals(Expression.DIV.toString())) {
            return new DivisibleExpression(this.input, Integer.valueOf(expressionParts[index.incrementAndGet()]));
        }
        if(part.equals(Expression.EQ.toString())) {
            return new EqualsExpression(this.input, Integer.valueOf(expressionParts[index.incrementAndGet()]));
        }
        if(part.equals(Expression.NEQ.toString())) {
            return new NotEqualsExpression(this.input, Integer.valueOf(expressionParts[index.incrementAndGet()]));
        }
        return null;
    }

    private IExpression checkForLogicalExpression(String[] expressionParts, IExpression parent) {
        String part = expressionParts[index.get()];
        if (part.equals(LogicalExpression.AND.toString())) {
            AndExpression and = new AndExpression(parent);
            increment();
            and.setRightSide(getExpression(expressionParts, and));
            return and;
        }
        if (part.equals(LogicalExpression.OR.toString())) {
            OrExpression or = new OrExpression(parent);
            increment();
            or.setRightSide(getExpression(expressionParts, or));
            return or;
        }
        return null;
    }

    private void reset() {
        if (index == null)
            index = new AtomicInteger(0);
        else
            index.set(0);
    }

    private void increment() {
        index.set(index.get()+1);
    }
}
