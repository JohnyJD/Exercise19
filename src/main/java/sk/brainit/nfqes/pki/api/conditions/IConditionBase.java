package sk.brainit.nfqes.pki.api.conditions;

public interface IConditionBase<T,I> {
    I perform(T value);
}
