package sk.brainit.nfqes.pki.api.conditions;

public interface IEvaluable<T> {
    boolean evaluate(T value);
}
