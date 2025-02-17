package sk.brainit.nfqes.pki.api.evaluators;

/**
 * Marks evaluator for implementing evaluation logic
 * @param <T> Input data
 * @param <I> Output data
 */
public interface IEvaluator<T, I>{
    I evaluate(T input);
}
