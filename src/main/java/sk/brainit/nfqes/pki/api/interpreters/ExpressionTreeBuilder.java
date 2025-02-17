package sk.brainit.nfqes.pki.api.interpreters;

import sk.brainit.nfqes.pki.api.interpreters.expressions.*;
import sk.brainit.nfqes.pki.api.interpreters.expressions.logicals.AndExpression;
import sk.brainit.nfqes.pki.api.interpreters.expressions.logicals.OrExpression;
import sk.brainit.nfqes.pki.api.interpreters.expressions.operations.DivisibleExpression;
import sk.brainit.nfqes.pki.api.interpreters.expressions.operations.EqualsExpression;
import sk.brainit.nfqes.pki.api.interpreters.expressions.operations.GroupExpression;
import sk.brainit.nfqes.pki.api.interpreters.expressions.operations.NotEqualsExpression;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Translates simplified string expression following simple rules
 * into complex numerical-logical operations
 * Rules:
 * operators - DIV, EQ, NEQ
 * logical operators - OR, AND
 * Syntax - DIV 2 OR EQ 7 - divisble by 2 or equals 7
 * ! Input is not parameter, because it is passed dynamically
 * More rules can be added as expressions
 */
public class ExpressionTreeBuilder implements IExpressionTreeBuilder {
    // Used as a Reference for steps of building
    private AtomicInteger index;
    private static ExpressionTreeBuilder instance;
    private Integer input;

    /**
     * Singleton
     */
    private ExpressionTreeBuilder() {
        reset();
    }

    public static ExpressionTreeBuilder getInstance() {
        if (instance == null) {
            instance = new ExpressionTreeBuilder();
        }
        return instance;
    }

    /**
     * Builds expression representation from string representation with specified rules
     * String expression is first splitted with gap
     * Then is recursively building expression
     * @param expression
     * @param input
     * @return
     */
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
        // Check for operations (e.g. DIV, EQ, ... ) and build
        IExpression expression = checkForExpression(expressionParts);
        if(expression != null) {
            increment();
            if (index.get() >= expressionParts.length) {
                return expression;
            }
            // Check if operation does have logical operator next
            IExpression logicalExpression = checkForLogicalExpression(expressionParts, expression);
            if(logicalExpression != null) {
                // If yes return logical operator instead
                // It encapsulates operation inside
                return logicalExpression;
            }
            // Invalid expression - operation needs logical expression next
            throw new RuntimeException("Invalid Expression - Missing logical expression");
        }
        // invalid expression - Unknown valid expression
        throw new RuntimeException("Invalid Expression - Unknown expression");
    }

    /**
     * Checks if part on current index is a operation
     * If yes builds operation
     * Shifts index one position in order to find value
     * Then returns built expression
     * Otherwise returns null
     * @param expressionParts Splitted parts of string expression
     * @return Expression or null
     */
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

    /**
     * Checks if part on current index is a logical operation (AND, OR) if not returns null
     * If yes builds operation
     * Left side operand is current parent expression passed as reference
     * Right side operand will be calculated - new parent will be this operator
     * @param expressionParts Splitted parts of string expression
     * @param parent Left side expression
     * @return Expression or null
     */
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

    /**
     * Resets index to 0
     */
    private void reset() {
        if (index == null)
            index = new AtomicInteger(0);
        else
            index.set(0);
    }

    /**
     * Increments index by 1
     */
    private void increment() {
        index.set(index.get()+1);
    }
}
