package sk.brainit.nfqes.pki.api.evaluators;

public interface IEvaluator<T, I>{
    I process(T value);
}
