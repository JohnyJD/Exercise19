package sk.brainit.nfqes.pki.api.conditions;

public interface IEvaluable<T,I> {
    I evaluate(T value);
}
