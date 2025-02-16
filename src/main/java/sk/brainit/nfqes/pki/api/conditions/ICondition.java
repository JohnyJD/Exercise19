package sk.brainit.nfqes.pki.api.conditions;

public interface ICondition<T>  {
    boolean check(T value);
}
