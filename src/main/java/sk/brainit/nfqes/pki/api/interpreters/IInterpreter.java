package sk.brainit.nfqes.pki.api.interpreters;

/**
 * Interpreter abstraction
 */
public interface IInterpreter {
    boolean interpret(String expression, Integer input);
}
