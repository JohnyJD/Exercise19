package sk.brainit.nfqes.pki.api.conditions;

public interface ICondition extends IConditionBase<Integer, String>  {
    String perform(Integer value);
}
