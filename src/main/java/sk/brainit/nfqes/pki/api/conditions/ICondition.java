package sk.brainit.nfqes.pki.api.conditions;

import sk.brainit.nfqes.pki.api.interpreters.IInterpreter;

/**
 * Condition abstraction
 * @param <T> Input data
 * @param <I> Output data
 */
public interface ICondition<T,I> {
    /**
     * Uses interpreter to translate expression and evaluates
     * @param interpreter Interpreter abstraction
     * @param input Input data T
     * @return Output data I
     */
    I evaluate(IInterpreter interpreter, T input);
}
