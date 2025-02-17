package sk.brainit.nfqes.pki.api.conditions;

import sk.brainit.nfqes.pki.api.interpreters.IInterpreter;

public interface ICondition<T,I> {
    I evaluate(IInterpreter interpreter, T input);
}
