package sk.brainit.nfqes.pki.api.evaluators;

public interface IEvaluator<T, I>{
    I evaluate(T value);
}
